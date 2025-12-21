package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class RoyalChestplate extends RoyalArmorPiece {
    JavaPlugin plugin;

    public RoyalChestplate(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.NETHERITE_CHESTPLATE,
                "Royal Guard Chestplate",
                List.of("Legendary Armor - Chestplate",
                        "Made from gold and iron this armor looks like its ceremonial",
                        "but actually protects its wearer much better than most other armors"),
                1000,
                11,
                EquipmentSlotGroup.FEET,
                2405
        );
    }
}
