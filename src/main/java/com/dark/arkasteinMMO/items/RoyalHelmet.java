package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class RoyalHelmet extends RoyalArmorPiece {
    JavaPlugin plugin;

    public RoyalHelmet(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.NETHERITE_HELMET,
                "Royal Guard Helmet",
                List.of("Legendary Armor - Helmet",
                        "Made from gold and iron this armor looks like its ceremonial",
                        "but actually protects its wearer much better than most other armors"),
                1000,
                8,
                EquipmentSlotGroup.FEET,
                2205
        );
    }
}
