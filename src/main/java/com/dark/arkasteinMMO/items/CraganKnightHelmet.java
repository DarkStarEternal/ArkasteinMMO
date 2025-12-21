package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CraganKnightHelmet extends CraganKnightPiece {
    JavaPlugin plugin;

    public CraganKnightHelmet(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.IRON_HELMET,
                "Cragan Knight Helmet",
                List.of("Armor - Helmet",
                        "Sturdy chainmail combined with steel plating and leather trims",
                        "are used to protect the Infantrists of the Cragan Union."),
                1000,
                4,
                EquipmentSlotGroup.HEAD,
                205
        );
    }
}
