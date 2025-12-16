package com.dark.arkasteinMMO.items;
import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class QuickFiringCrossbow {

    private final JavaPlugin plugin;

    public static final int MAX_SHOTS = 5;

    public QuickFiringCrossbow(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack item() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        meta.setDisplayName("Quick-Fire Crossbow");
        meta.setLore(List.of(
                "Crossbow - Dual Hand",
                "Ablities:",
                "Quickfire - Burst: Fires all stored up arrows in a single, rapid burst",
                "Magazine: Can store up to five arrows to shoot."
        ));

        meta.setCustomModelData(3001);

        meta.getPersistentDataContainer().set(
                new NamespacedKey(plugin, "quickfirecrossbow"),
                PersistentDataType.BYTE,
                (byte) 1
        );

        meta.getPersistentDataContainer().set(
                ArkasteinMMO.ISQUICKFIRE,
                PersistentDataType.BYTE,
                (byte) 1
        );

        meta.getPersistentDataContainer().set(
                ArkasteinMMO.STORED_SHOTS_KEY,
                PersistentDataType.INTEGER,
                0
        );

        item.setItemMeta(meta);
        return item;
    }
}
