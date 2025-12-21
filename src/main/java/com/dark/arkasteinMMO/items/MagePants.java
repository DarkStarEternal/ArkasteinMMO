package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MagePants extends MageRobePiece{
    JavaPlugin plugin;

    public MagePants(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack MagePantsItem() {
        return create(
                Material.LEATHER_LEGGINGS,
                "Mage Pants",
                List.of("Magical Armor - Pants", "Light equipment worn by mages."),
                1001,
                4,
                EquipmentSlotGroup.LEGS,
                155
        );
    }
}
