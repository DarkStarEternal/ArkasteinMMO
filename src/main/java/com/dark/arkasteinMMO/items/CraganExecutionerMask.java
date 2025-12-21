package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CraganExecutionerMask extends CraganExecutionerPiece {
    JavaPlugin plugin;

    public CraganExecutionerMask(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack Item() {
        return create(
                Material.IRON_HELMET,
                "Cragan Executioner Mask",
                List.of("Armor - Helmet",
                        "A simple shaped steel plate with Holes bolted in.",
                        "Intimidating towards the enemy and serving limited protection for its user."),
                1001,
                5,
                EquipmentSlotGroup.HEAD,
                225
        );
    }
}
