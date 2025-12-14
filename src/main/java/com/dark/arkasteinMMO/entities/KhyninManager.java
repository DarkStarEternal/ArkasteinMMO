package com.dark.arkasteinMMO.entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import net.kyori.adventure.text.Component;

import java.util.concurrent.ThreadLocalRandom;

public class KhyninManager {

    private final JavaPlugin plugin;
    public static NamespacedKey minionKey;

    public KhyninManager(JavaPlugin plugin) {
        this.plugin = plugin;
        minionKey = new NamespacedKey(plugin, "khynin_minion");
    }

    /**
     * Spawn a single Khynin minion near a location
     */
    public static Monster spawnMinion(Location location) {
        Location spawnLoc = location.clone().add(
                ThreadLocalRandom.current().nextDouble(-3, 3),
                0,
                ThreadLocalRandom.current().nextDouble(-3, 3)
        );

        Zombie minion = spawnLoc.getWorld().spawn(spawnLoc, Zombie.class);

        // Tag for identification
        minion.getPersistentDataContainer().set(minionKey, PersistentDataType.BYTE, (byte) 1);

        // Stats
        minion.setCustomName("Khynin");
        minion.setCustomNameVisible(false);
        minion.getAttribute(Attribute.MAX_HEALTH).setBaseValue(34);
        minion.setHealth(20);
        minion.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(4);
        minion.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.27);
        minion.setRemoveWhenFarAway(true);

        return minion;
    }

    public static void spawnMinionWave(Location location, int count) {
        for (int i = 0; i < count; i++) {
            spawnMinion(location);
        }
    }
}
