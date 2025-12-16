package com.dark.arkasteinMMO.items;

import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CraganLongBow {
    JavaPlugin plugin;

    public CraganLongBow(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack CraganLongBowItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        meta.setDisplayName("Cragan Longbow");
        meta.setLore(List.of(
                "Long Bow - Dual hand",
                "Developed for maximum reach",
                "Ablities:",
                "Long Shot: Has much higher reach than regular bows"
        ));
        meta.setCustomModelData(1);

        meta.getPersistentDataContainer().set(
                ArkasteinMMO.ISTWOHANDED,
                PersistentDataType.BYTE,
                (byte) 1
        );
        meta.getPersistentDataContainer().set(
                ArkasteinMMO.ISLONGBOW,
                PersistentDataType.BYTE,
                (byte) 1
        );
        meta.getPersistentDataContainer().set(
                ArkasteinMMO.VELOCITY_MULTIPLIER_KEY,
                PersistentDataType.DOUBLE,
                1.75
        );
        meta.getPersistentDataContainer().set(
                new NamespacedKey(plugin, "cragan_longbow"),
                PersistentDataType.BYTE,
                (byte) 1
        );

        item.setItemMeta(meta);
        return item;
    }
}
