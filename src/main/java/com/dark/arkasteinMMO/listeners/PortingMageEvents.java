package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.entities.PortingMageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class PortingMageEvents implements Listener {

    private final PortingMageManager manager;
    private final JavaPlugin plugin;

    public PortingMageEvents(PortingMageManager manager, JavaPlugin plugin) {
        this.manager = manager;
        this.plugin = plugin;
        // Register events
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) return;

        if (e.getView().getTopInventory().getHolder() instanceof WanderingTrader trader) {
            if (trader.getPersistentDataContainer().has(manager.portingMageKey, PersistentDataType.BYTE)) {
                // Schedule despawn next tick to ensure trade completes
                Bukkit.getScheduler().runTask(plugin, trader::remove);
            }
        }
    }
}
