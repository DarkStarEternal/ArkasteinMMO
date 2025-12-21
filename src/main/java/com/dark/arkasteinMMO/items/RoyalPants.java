package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class RoyalPants extends RoyalArmorPiece {
    JavaPlugin plugin;

    public RoyalPants(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.NETHERITE_LEGGINGS,
                "Royal Guard Pants",
                List.of("Legendary Armor - Pants",
                        "Made from gold and iron this armor looks like its ceremonial",
                        "but actually protects its wearer much better than most other armors"),
                1000,
                9,
                EquipmentSlotGroup.LEGS,
                2305
        );
    }
}
