package com.dark.arkasteinMMO.items;

import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.N;

import java.util.List;
import java.util.UUID;

public class FlamingSledgehammer {
    JavaPlugin plugin;

    public FlamingSledgehammer(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack FlamnigSledgehammerItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = item.getItemMeta();

        try {
            AttributeModifier damageModifier = new AttributeModifier(
                    UUID.randomUUID(),
                    "flaming_hammer_damage",
                    35.4,
                    AttributeModifier.Operation.ADD_NUMBER,
                    EquipmentSlot.HAND
            );
            AttributeModifier speedModifier = new AttributeModifier(
                    UUID.randomUUID(),
                    "flaming_hammer_speed",
                    -0.6,
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
            meta.setDisplayName("Flaming Sledgehammer");
            meta.setLore(List.of("Legendary Magical Sledgehammer - Dual hand",
                    "Enchanted with ember spells, this legendary battle mows down all your enemies.",
                    "Ablities:",
                    "Flame tip: Sets hit targets on fire.",
                    "Magical mend: Repairs itself using XP."));
            meta.setCustomModelData(1);
            meta.addEnchant(Enchantment.MENDING, 1,true);
            meta.addEnchant(Enchantment.FIRE_ASPECT, 2,true);

            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "flamingsledgehammer"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );

            meta.getPersistentDataContainer().set(
                    ArkasteinMMO.ISTWOHANDED,
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

}
