package com.dark.arkasteinMMO.items;

import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class MageHood extends MageRobePiece {
    JavaPlugin plugin;

    public MageHood(JavaPlugin plugin) {
        super(plugin);
    }

    public ItemStack MageHoodItem() {
        return create(
                Material.LEATHER_HELMET,
                "Mage Hood",
                List.of("Magical Armor - Hood", "Light equipment worn by mages."),
                1001,
                3,
                EquipmentSlotGroup.HEAD,
                105
        );
    }
}
