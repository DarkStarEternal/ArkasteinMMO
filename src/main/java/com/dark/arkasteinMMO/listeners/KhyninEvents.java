package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.entities.KhyninManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class KhyninEvents implements Listener {

    private final KhyninManager manager;

    public KhyninEvents(KhyninManager manager, JavaPlugin plugin) {
        this.manager = manager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        if (!(event.getEntity() instanceof Monster mob)) return;
        if (!mob.getPersistentDataContainer().has(manager.minionKey, PersistentDataType.BYTE)) return;

        if (!(event.getTarget() instanceof Player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Monster mob)) return;
        if (!mob.getPersistentDataContainer().has(manager.minionKey, PersistentDataType.BYTE)) return;

        if (!(event.getDamager() instanceof Player)) {
            event.setCancelled(true);
        }
    }
}
