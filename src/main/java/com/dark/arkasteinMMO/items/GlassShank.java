package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public class GlassShank {
    JavaPlugin plugin;

    public GlassShank(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack GlassShankItem() {
        ItemStack item = new ItemStack(Material.STONE_AXE);
        ItemMeta meta = item.getItemMeta();

        try {
            AttributeModifier damageModifier = new AttributeModifier(
                    UUID.randomUUID(),
                    "glass_shank_damage",
                    5.0,
                    AttributeModifier.Operation.ADD_NUMBER,
                    EquipmentSlot.HAND
            );
            AttributeModifier speedModifier = new AttributeModifier(
                    UUID.randomUUID(),
                    "glass_shank_speed",
                    1.9,
                    AttributeModifier.Operation.ADD_NUMBER,
                    EquipmentSlot.HAND
            );

            meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
            meta.removeAttributeModifier(Attribute.ATTACK_SPEED);
            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (meta != null) {
            meta.setDisplayName("Glass Shank");
            meta.setLore(List.of(
                    "Improvised small weapon - Single hand",
                    "Everything can be used as a weapon, even shattered pieces of glass.",
                    "Abilities:",
                    "Cracked: May cause bleed damage to hit enemies at the cost of five durablity."
            ));
            meta.setCustomModelData(1);

            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "glassshank"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

}
