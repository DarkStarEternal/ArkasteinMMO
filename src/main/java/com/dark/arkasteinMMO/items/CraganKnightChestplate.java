package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CraganKnightChestplate extends CraganKnightPiece {
    JavaPlugin plugin;

    public CraganKnightChestplate(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.CHAINMAIL_CHESTPLATE,
                "Cragan Knight Chestplate",
                List.of("Armor - Chestplate",
                        "Sturdy chainmail combined with steel plating and leather trims",
                        "are used to protect the Infantrists of the Cragan Union."),
                1000,
                6,
                EquipmentSlotGroup.CHEST,
                275
        );
    }
}
