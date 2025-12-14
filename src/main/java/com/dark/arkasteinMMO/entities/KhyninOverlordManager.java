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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import net.kyori.adventure.text.Component;

import java.util.HashMap;
import java.util.Map;

public class KhyninOverlordManager {

    private static final double BOSSBAR_RANGE = 40.0;

    public final NamespacedKey overlordProjectileKey;
    public final NamespacedKey minionStepKey;


    private final JavaPlugin plugin;
    public final NamespacedKey overlordKey;

    private final Map<IronGolem, BukkitTask> shootingTasks = new HashMap<>();
    private final Map<IronGolem, BukkitTask> updateTasks = new HashMap<>();
    private final Map<IronGolem, BossBar> bossBars = new HashMap<>();

    public KhyninOverlordManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.overlordKey = new NamespacedKey(plugin, "khynin_overlord");
        this.overlordProjectileKey = new NamespacedKey(plugin, "khynin_projectile");
        this.minionStepKey = new NamespacedKey(plugin, "khynin_minion_step");


    }

    public void spawnOverlord(Player player) {
        IronGolem overlord = player.getWorld().spawn(player.getLocation(), IronGolem.class);

        overlord.getPersistentDataContainer().set(
                minionStepKey,
                PersistentDataType.INTEGER,
                0
        );

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

            Player target = overlord.getNearbyEntities(20, 10, 20).stream()
                    .filter(e -> e instanceof Player)
                    .map(e -> (Player) e)
                    .findFirst()
                    .orElse(null);

            if (target != null) {
                overlord.setTarget(target);
            }

            if (target != null) {
                Snowball proj = overlord.getWorld().spawn(overlord.getEyeLocation(), Snowball.class);

                proj.setItem(new ItemStack(Material.MUD));

                proj.setVelocity(
                        target.getEyeLocation()
                                .toVector()
                                .subtract(overlord.getEyeLocation().toVector())
                                .normalize()
                                .multiply(1.2)
                );

                proj.setShooter(overlord);

                proj.getPersistentDataContainer().set(
                        overlordProjectileKey,
                        PersistentDataType.BYTE,
                        (byte) 1
                );

            }
        }, 0L, 200L); // every ten seconds

        BukkitTask task2 = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (!overlord.isValid()) {
                cancelOverlordTask(overlord);
                return;
            }

            Player target = overlord.getNearbyEntities(20, 10, 20).stream()
                    .filter(e -> e instanceof Player)
                    .map(e -> (Player) e)
                    .findFirst()
                    .orElse(null);

            if (target != null) {
                overlord.setTarget(target);
            }

            double maxHealth = overlord.getAttribute(Attribute.MAX_HEALTH).getValue();
            double currentHealth = overlord.getHealth();
            double healthPercent = currentHealth / maxHealth;
            bossBar.setProgress(Math.max(0.0, Math.min(1.0, healthPercent)));

            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.getWorld().equals(overlord.getWorld())) {
                    bossBar.removePlayer(online);
                    continue;
                }

                double distance = online.getLocation().distance(overlord.getLocation());
                if (distance <= BOSSBAR_RANGE) {
                    bossBar.addPlayer(online);
                } else {
                    bossBar.removePlayer(online);
                }
            }

            int previousStep = overlord.getPersistentDataContainer().get(
                    minionStepKey,
                    PersistentDataType.INTEGER
            );

            int currentStep = (int) ((maxHealth - currentHealth) / 20);

            if (currentStep > previousStep) {
                int stepsToProcess = currentStep - previousStep;

                for (int i = 0; i < stepsToProcess; i++) {
                    KhyninManager.spawnMinionWave(overlord.getLocation(), 3);
                }

                overlord.getPersistentDataContainer().set(
                        minionStepKey,
                        PersistentDataType.INTEGER,
                        currentStep
                );
            }

        }, 0L, 2L);
        shootingTasks.put(overlord, task);
        updateTasks.put(overlord, task2);

        player.sendMessage(Component.text("A Khynin Overlord has spawned!"));
    }

    public void cancelOverlordTask(IronGolem overlord) {
        if (shootingTasks.containsKey(overlord)) {
            shootingTasks.get(overlord).cancel();
            shootingTasks.remove(overlord);
        }
        if (updateTasks.containsKey(overlord)) {
            updateTasks.get(overlord).cancel();
            updateTasks.remove(overlord);
        }
        if (bossBars.containsKey(overlord)) {
            bossBars.get(overlord).removeAll();
            bossBars.remove(overlord);
        }
    }
}
