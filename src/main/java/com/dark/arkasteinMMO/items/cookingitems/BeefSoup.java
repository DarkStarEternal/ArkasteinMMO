package com.dark.arkasteinMMO.items.cookingitems;

import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public class BeefSoup {
    private final JavaPlugin plugin;
    public static NamespacedKey BEEF_SOUP_KEY;

    public BeefSoup(JavaPlugin plugin) {
        this.plugin = plugin;
        BEEF_SOUP_KEY = new NamespacedKey(plugin, "beef_soup");
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BEETROOT_SOUP);
        ItemMeta meta = item.getItemMeta();

        if (meta == null) return item;

        // Display
        meta.setDisplayName("§bBeef Soup");
        meta.setLore(List.of(
                "§bCooked Food"
        ));
        meta.setCustomModelData(1);

        meta.getPersistentDataContainer().set(
                new NamespacedKey(plugin, "custom_hunger"),
                PersistentDataType.INTEGER,
                8
        );

        meta.getPersistentDataContainer().set(
                new NamespacedKey(plugin, "custom_saturation"),
                PersistentDataType.DOUBLE,
                9.6
        );

        meta.getPersistentDataContainer().set(
                BEEF_SOUP_KEY,
                PersistentDataType.BYTE,
                (byte) 1
        );

        item.setItemMeta(meta);
        return item;
    }
}
