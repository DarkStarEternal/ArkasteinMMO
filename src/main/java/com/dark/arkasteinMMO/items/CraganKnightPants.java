package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CraganKnightPants extends CraganKnightPiece {
    JavaPlugin plugin;

    public CraganKnightPants(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.IRON_LEGGINGS,
                "Cragan Knight Pants",
                List.of("Armor - Pants",
                        "Sturdy chainmail combined with steel plating and leather trims",
                        "are used to protect the Infantrists of the Cragan Union."),
                1000,
                5,
                EquipmentSlotGroup.LEGS,
                205
        );
    }
}
