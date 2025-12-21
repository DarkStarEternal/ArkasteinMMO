package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MageRobe extends MageRobePiece{
    JavaPlugin plugin;

    public MageRobe(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack MageRobeItem() {
        return create(
                Material.LEATHER_CHESTPLATE,
                "Mage Robe",
                List.of("Magical Armor - Robe",
                        "Light equipment worn by mages."
                ),
                1001,
                5,
                EquipmentSlotGroup.BODY,
                165
        );
    }
}


