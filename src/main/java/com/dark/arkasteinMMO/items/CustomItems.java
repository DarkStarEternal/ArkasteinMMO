package com.dark.arkasteinMMO.items;

import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static org.bukkit.attribute.Attribute.ATTACK_DAMAGE;

public class CustomItems {
    private final JavaPlugin plugin;

    public CustomItems(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack createIronLongsword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        AttributeModifier damageModifier = new AttributeModifier(
                UUID.randomUUID(),             // unique ID
                "generic.attackDamage",        // name
                7.0,                           // value (+2 damage)
                AttributeModifier.Operation.ADD_NUMBER // add directly
        );
        AttributeModifier speedModifier = new AttributeModifier(
                UUID.randomUUID(),             // unique ID
                "generic.attackSpeed",        // name
                1.3,                           // value (+2 damage)
                AttributeModifier.Operation.ADD_NUMBER // add directly
        );

        if (meta != null) {
            meta.setDisplayName("Iron Longsword");
            meta.setLore(List.of("Longer than a normal sword, this weapon should rather not be used in crowded spaces."));
            meta.setCustomModelData(7);
            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "iron_longsword"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack createGoldLongsword() {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        AttributeModifier damageModifier = new AttributeModifier(
                UUID.randomUUID(),             // unique ID
                "generic.attackDamage",        // name
                5.5,                           // value (+2 damage)
                AttributeModifier.Operation.ADD_NUMBER // add directly
        );
        AttributeModifier speedModifier = new AttributeModifier(
                UUID.randomUUID(),             // unique ID
                "generic.attackSpeed",        // name
                1.3,                           // value (+2 damage)
                AttributeModifier.Operation.ADD_NUMBER // add directly
        );

        if (meta != null) {
            meta.setDisplayName("Gold Longsword");
            meta.setLore(List.of("Longer than a normal sword, this weapon should rather not be used in crowded spaces."));
            meta.setCustomModelData(7);
            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "gold_longsword"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

    public ItemStack createDiamondLongsword() {
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

    public ItemStack createNetheriteLongsword() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        AttributeModifier damageModifier = new AttributeModifier(
                UUID.randomUUID(),             // unique ID
                "generic.attackDamage",        // name
                9.5,                           // value (+2 damage)
                AttributeModifier.Operation.ADD_NUMBER // add directly
        );
        AttributeModifier speedModifier = new AttributeModifier(
                UUID.randomUUID(),             // unique ID
                "generic.attackSpeed",        // name
                1.3,                           // value (+2 damage)
                AttributeModifier.Operation.ADD_NUMBER // add directly
        );

        if (meta != null) {
            meta.setDisplayName("Netherite Longsword");
            meta.setLore(List.of("Longer than a normal sword, this weapon should rather not be used in crowded spaces."));
            meta.setCustomModelData(7);
            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "netherite_longsword"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }
}