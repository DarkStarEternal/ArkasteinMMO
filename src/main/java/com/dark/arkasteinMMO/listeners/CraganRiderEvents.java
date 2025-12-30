package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.entities.CraganRiderManager;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.net.http.WebSocket;

public class CraganRiderEvents implements Listener {
    private final CraganRiderManager manager;
    private static final double RIDER_CHANCE = 0.01; // 1%

    public CraganRiderEvents(CraganRiderManager manager) {
        this.manager = manager;
    }

    @EventHandler(ignoreCancelled = true)
    public void onZombieSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL) return;
        if (event.getEntityType() != EntityType.ZOMBIE) return;

        if (Math.random() > RIDER_CHANCE) return;

        Location loc = event.getLocation();

        // Spawn an ADDITIONAL rider zombie
        manager.spawn(loc.getWorld(), loc);
    }
}
