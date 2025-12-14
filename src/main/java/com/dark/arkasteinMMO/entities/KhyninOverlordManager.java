package com.dark.arkasteinMMO.entities;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import net.kyori.adventure.text.Component;

import java.util.HashMap;
import java.util.Map;

public class KhyninOverlordManager {

    private final JavaPlugin plugin;
    public final NamespacedKey overlordKey;

    // Keep track of tasks and boss bars
    private final Map<IronGolem, BukkitTask> overlordTasks = new HashMap<>();
    private final Map<IronGolem, BossBar> bossBars = new HashMap<>();

    public KhyninOverlordManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.overlordKey = new NamespacedKey(plugin, "khynin_overlord");
    }

    public void spawnOverlord(Player player) {
        IronGolem overlord = player.getWorld().spawn(player.getLocation(), IronGolem.class);

        // Tag for identification
        overlord.getPersistentDataContainer().set(overlordKey, PersistentDataType.BYTE, (byte)1);

        // Stats
        overlord.getAttribute(Attribute.MAX_HEALTH).setBaseValue(200);
        overlord.setHealth(200);
        overlord.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.15);
        overlord.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(15);
        overlord.setCustomName("Khynin Overlord");
        overlord.setCustomNameVisible(true);

        // BossBar setup
        BossBar bossBar = Bukkit.createBossBar("Khynin Overlord", BarColor.RED, BarStyle.SEGMENTED_20);
        bossBar.setProgress(1.0);
        bossBar.addPlayer(player); // show to the player who spawned it
        bossBars.put(overlord, bossBar);

        // Ranged attack task
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (!overlord.isValid()) {
                cancelOverlordTask(overlord);
                return;
            }

            // Update boss bar health
            double healthPercent = overlord.getHealth() / overlord.getAttribute(Attribute.MAX_HEALTH).getValue();
            bossBar.setProgress(Math.max(0, healthPercent));

            // Find a nearby player to target
            Player target = overlord.getNearbyEntities(10,10,10).stream()
                    .filter(e -> e instanceof Player)
                    .map(e -> (Player)e)
                    .findFirst().orElse(null);

            if (target != null) {
                Snowball proj = overlord.getWorld().spawn(overlord.getEyeLocation(), Snowball.class);
                proj.setVelocity(target.getLocation().toVector().subtract(overlord.getLocation().toVector()).normalize().multiply(1.0));
                proj.setShooter(overlord);
            }
        }, 0L, 200L); // every second

        overlordTasks.put(overlord, task);

        player.sendMessage(Component.text("A Khynin Overlord has spawned!"));
    }

    public void cancelOverlordTask(IronGolem overlord) {
        if (overlordTasks.containsKey(overlord)) {
            overlordTasks.get(overlord).cancel();
            overlordTasks.remove(overlord);
        }
        if (bossBars.containsKey(overlord)) {
            bossBars.get(overlord).removeAll();
            bossBars.remove(overlord);
        }
    }
}
