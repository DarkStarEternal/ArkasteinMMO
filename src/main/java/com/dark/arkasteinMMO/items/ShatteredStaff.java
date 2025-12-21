package com.dark.arkasteinMMO.items;

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

public class ShatteredStaff {
    JavaPlugin plugin;

    public ShatteredStaff(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack ShatteredStaffItem() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("Shattered Staff");
            meta.setLore(List.of(
                    "Magical Improvised Small Staff - Single hand",
                    "This staff is just a part of what it once was. Maybe it can be reassembled.",
                    "Abilities:",
                    "Shattered: Can be combined with a fitting missing piece to restore its former glory"
            ));
            meta.setCustomModelData(1);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "shatteredstaff"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

}
