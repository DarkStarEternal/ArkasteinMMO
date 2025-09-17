package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

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

        AttributeModifier damageModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.attackDamage",
                38.0,
                AttributeModifier.Operation.ADD_NUMBER
        );
        AttributeModifier speedModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.attackSpeed",
                -0.3,
                AttributeModifier.Operation.ADD_NUMBER
        );

        if (meta != null) {
            meta.setDisplayName("Flaming Sledgehammer");
            meta.setLore(List.of("Magical Sledgehammer - Dual hand","Enchanted with ember spells, Hammers like these are perfect for battle.", "Ablities:", "Flame tip: Sets hit targets on fire.", "Magical mend: Repairs itself using XP."));
            meta.setCustomModelData(1);
            meta.addEnchant(Enchantment.MENDING, 1,true);
            meta.addEnchant(Enchantment.FIRE_ASPECT, 2,true);
            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "flaming_sledgehammer"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

}
