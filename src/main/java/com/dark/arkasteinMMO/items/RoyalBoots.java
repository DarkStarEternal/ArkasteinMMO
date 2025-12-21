package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class RoyalBoots extends RoyalArmorPiece {
    JavaPlugin plugin;

    public RoyalBoots(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.NETHERITE_BOOTS,
                "Royal Guard Boots",
                List.of("Legendary Armor - Boots",
                        "Made from gold and iron this armor looks like its ceremonial",
                        "but actually protects its wearer much better than most other armors"),
                1000,
                7,
                EquipmentSlotGroup.FEET,
                2005
        );
    }
}
