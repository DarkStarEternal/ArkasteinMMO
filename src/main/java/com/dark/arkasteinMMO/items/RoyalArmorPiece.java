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

public abstract class RoyalArmorPiece {
    protected final JavaPlugin plugin;

    public RoyalArmorPiece(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    protected ItemStack create(
            Material material,
            String name,
            List<String> lore,
            int customModelData,
            double armor,
            EquipmentSlotGroup slot,
            int maxDurability
    ) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.setCustomModelData(customModelData);

        meta.addAttributeModifier(
                Attribute.ARMOR,
                new AttributeModifier(
                        new NamespacedKey(plugin, name.toLowerCase().replace(" ", "_") + "_armor"),
                        armor,
                        AttributeModifier.Operation.ADD_NUMBER,
                        slot
                )
        );

        meta.getPersistentDataContainer().set(
                ArkasteinMMO.ROYAL_ARMOR_PIECE,
                PersistentDataType.BYTE,
                (byte) 1
        );

        meta.getPersistentDataContainer().set(
                ArkasteinMMO.CUSTOM_DURABILITY,
                PersistentDataType.INTEGER,
                maxDurability
        );

        meta.getPersistentDataContainer().set(
                ArkasteinMMO.MAX_CUSTOM_DURABILITY,
                PersistentDataType.INTEGER,
                maxDurability
        );

        item.setItemMeta(meta);
        return item;
    }
}
