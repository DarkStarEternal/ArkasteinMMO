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
import java.util.UUID;

public class NetheriteLongsword {
    JavaPlugin plugin;

    public NetheriteLongsword(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack NetheriteLongSwordItem() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        AttributeModifier damageModifier = new AttributeModifier(
                new NamespacedKey(plugin, "netherite_longsword_damage"),
                12,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        );

        AttributeModifier speedModifier = new AttributeModifier(
                new NamespacedKey(plugin, "netherite_longsword_speed"),
                0.2,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        );

        if (meta != null) {
            meta.setDisplayName("Netherite Longsword");
            meta.setLore(List.of("Longsword - Dual hand","Longer than a normal sword, this weapon should rather not be used in crowded spaces."));
            meta.setCustomModelData(1);

            meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
            meta.removeAttributeModifier(Attribute.ATTACK_SPEED);

            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "netherite_longsword"),
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
