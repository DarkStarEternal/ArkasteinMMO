package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.entities.KhyninOverlordManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.IronGolem;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class KhyninOverlordEvents implements Listener {

    private final KhyninOverlordManager manager;

    public KhyninOverlordEvents(KhyninOverlordManager manager, JavaPlugin plugin) {
        this.manager = manager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof IronGolem overlord)) return;

        if (overlord.getPersistentDataContainer().has(manager.overlordKey, PersistentDataType.BYTE)) {
            manager.cancelOverlordTask(overlord);

            e.getDrops().clear();
            e.getEntity().getWorld().dropItemNaturally(overlord.getLocation(), new ItemStack(Material.IRON_BLOCK, 5));
        }
    }
}
