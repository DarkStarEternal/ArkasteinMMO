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

public class DiamondLongsword {
    JavaPlugin plugin;

    public DiamondLongsword(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack DiamondLongSwordItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        AttributeModifier damageModifier = new AttributeModifier(
                UUID.randomUUID(),             // unique ID
                "generic.attackDamage",        // name
                8.5,                           // value (+2 damage)
                AttributeModifier.Operation.ADD_NUMBER // add directly
        );
        AttributeModifier speedModifier = new AttributeModifier(
                UUID.randomUUID(),             // unique ID
                "generic.attackSpeed",        // name
                1.3,                           // value (+2 damage)
                AttributeModifier.Operation.ADD_NUMBER // add directly
        );

        if (meta != null) {
            meta.setDisplayName("Diamond Longsword");
            meta.setLore(List.of("Longer than a normal sword, this weapon should rather not be used in crowded spaces."));
            meta.setCustomModelData(7);
            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "diamod_longsword"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

}
