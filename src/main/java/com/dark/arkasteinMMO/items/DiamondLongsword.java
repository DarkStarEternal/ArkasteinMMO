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
                UUID.randomUUID(),
                "generic.attackDamage",
                1.6,
                AttributeModifier.Operation.ADD_NUMBER
        );
        AttributeModifier speedModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.attackSpeed",
                -0.3,
                AttributeModifier.Operation.ADD_NUMBER
        );

        if (meta != null) {
            meta.setDisplayName("Diamond Longsword");
            meta.setLore(List.of("Longsword - Dual hand","Longer than a normal sword, this weapon should rather not be used in crowded spaces."));
            meta.setCustomModelData(1);
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
