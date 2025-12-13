package com.dark.arkasteinMMO.items;

import com.dark.arkasteinMMO.ArkasteinMMO;
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

    private final JavaPlugin plugin;

    public DiamondLongsword(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        if (meta == null) return item;

        // Display
        meta.setDisplayName("§bDiamond Longsword");
        meta.setLore(List.of(
                "§7Longsword - Two-handed",
                "§7Longer than a normal sword.",
                "§7Should not be used in crowded spaces."
        ));
        meta.setCustomModelData(1);

        // Attributes
        meta.addAttributeModifier(
                Attribute.ATTACK_DAMAGE,
                new AttributeModifier(
                        UUID.randomUUID(),
                        "diamond_longsword_damage",
                        1.6,
                        AttributeModifier.Operation.ADD_NUMBER
                )
        );

        meta.addAttributeModifier(
                Attribute.ATTACK_SPEED,
                new AttributeModifier(
                        UUID.randomUUID(),
                        "diamond_longsword_speed",
                        -0.3,
                        AttributeModifier.Operation.ADD_NUMBER
                )
        );

        // 🔑 Persistent data used to detect & remove from offhand
        meta.getPersistentDataContainer().set(
                ArkasteinMMO.ISTWOHANDED,
                PersistentDataType.BYTE,
                (byte) 1
        );

        item.setItemMeta(meta);
        return item;
    }
}
