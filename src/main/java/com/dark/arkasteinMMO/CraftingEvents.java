package com.dark.arkasteinMMO;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CraftingEvents implements Listener {

    private final Plugin plugin;

    public CraftingEvents(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {

        ItemStack result = event.getCurrentItem();
        if (result == null) return;

        ItemMeta meta = result.getItemMeta();
        if (meta == null) return;

        if (meta.hasCustomModelData() && meta.getCustomModelData() ==7 ){
            result = new CustomItems((JavaPlugin) plugin).createIronLongsword();
            event.getInventory().setResult(result);
        }
    }
}
