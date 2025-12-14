package com.dark.arkasteinMMO.items;

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
import java.util.UUID;

public class Sharpenedtwig {
    JavaPlugin plugin;

    public Sharpenedtwig(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack SharpenedtwigItem() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        AttributeModifier damageModifier = new AttributeModifier(
                new NamespacedKey(plugin, "sharpenedtwig_damage"),
                1.5,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        );

        AttributeModifier speedModifier = new AttributeModifier(
                new NamespacedKey(plugin, "sharpenedtwig_speed"),
                1.4,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        );

        if (meta != null) {
            meta.setDisplayName("Sharpened Twig - Single hand");
            meta.setLore(List.of("Improvised small weapon","Nothing more than a stick sharpened with a stone."));
            meta.setCustomModelData(1);

            meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
            meta.removeAttributeModifier(Attribute.ATTACK_SPEED);

            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "sharpenedtwig"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

}
