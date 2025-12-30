package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.entities.CraganKnightManager;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class CraganKnightEvents implements Listener {
    private final CraganKnightManager manager;
    private final JavaPlugin plugin;
    private static final double ARMORED_CHANCE = 0.08;
    private static final double DOUBLE_SPAWN_CHANCE = 0.23;// 23%

    public CraganKnightEvents(CraganKnightManager manager, JavaPlugin plugin) {
        this.manager = manager;
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onZombieSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL) return;
        if (event.getEntityType() != EntityType.ZOMBIE) return;
        if (event.getEntity().getPersistentDataContainer().has(
                new NamespacedKey(plugin, "custom_zombie"),
                PersistentDataType.BYTE
        )) return;


        if (Math.random() > ARMORED_CHANCE) return;

        Location loc = event.getLocation();

        // Spawn an ADDITIONAL armored zombie
        manager.spawn(loc.getWorld(), loc);

        if (Math.random() > DOUBLE_SPAWN_CHANCE) return;

        manager.spawn(loc.getWorld(), loc);
        manager.spawn(loc.getWorld(), loc);
    }
}
