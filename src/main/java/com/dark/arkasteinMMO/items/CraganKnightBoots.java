package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CraganKnightBoots extends CraganKnightPiece {
    JavaPlugin plugin;

    public CraganKnightBoots(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.LEATHER_BOOTS,
                "Cragan Knight Boots",
                List.of("Armor - Boots",
                        "Sturdy chainmail combined with steel plating and leather trims",
                        "are used to protect the Infantrists of the Cragan Union."),
                1000,
                3,
                EquipmentSlotGroup.FEET,
                205
        );
    }
}
