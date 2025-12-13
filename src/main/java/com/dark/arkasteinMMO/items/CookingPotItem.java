package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CookingPotItem {

    private final JavaPlugin plugin;
    public static NamespacedKey COOKING_POT_KEY;

    public CookingPotItem(JavaPlugin plugin) {
        this.plugin = plugin;
        COOKING_POT_KEY = new NamespacedKey(plugin, "cooking_pot_item");
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CAULDRON);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        meta.setDisplayName("§6Cooking Pot");
        meta.setLore(List.of(
                "§7Place this to create a Cooking Pot block",
                "§7Use it to cook custom recipes!"
        ));

        meta.getPersistentDataContainer().set(
                COOKING_POT_KEY,
                PersistentDataType.BYTE,
                (byte)1
        );

        item.setItemMeta(meta);
        return item;
    }
}
