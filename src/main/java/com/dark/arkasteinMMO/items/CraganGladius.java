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

public class CraganGladius {
    JavaPlugin plugin;

    public CraganGladius(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack Item() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        try {
            AttributeModifier damageModifier = new AttributeModifier(
                    new NamespacedKey(plugin, "cragan_gladius_damage"),
                    7,
                    AttributeModifier.Operation.ADD_NUMBER,
                    EquipmentSlotGroup.HAND
            );
            AttributeModifier speedModifier = new AttributeModifier(
                    new NamespacedKey(plugin, "cragan_gladius_speed"),
                    0.6,
                    AttributeModifier.Operation.ADD_NUMBER,
                    EquipmentSlotGroup.HAND
            );

            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);

        } catch (Exception e) {
            e.printStackTrace();
        }

        meta.setDisplayName("Cragan Gladius");
        meta.setLore(List.of(
                "Gladius Type Sword - Single hand",
                "Small and perfect for close quarters battles."
        ));
        meta.setCustomModelData(3);

        meta.getPersistentDataContainer().set(
                new NamespacedKey(plugin, "cragan_gladius"),
                PersistentDataType.BYTE,
                (byte) 1
        );

        item.setItemMeta(meta);
        return item;
    }
}
