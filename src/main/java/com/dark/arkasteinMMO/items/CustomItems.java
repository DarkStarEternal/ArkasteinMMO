package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CustomItems {
    private final JavaPlugin plugin;

    public CustomItems(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack createIronLongsword() {
        ItemStack item = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Iron Longsword");
            meta.setLore(List.of("Longer than a normal sword, this weapon should rather not be used in crowded spaces."));
            meta.setCustomModelData(7);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "iron_longsword"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }
}