package com.dark.arkasteinMMO.items;
import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ShieldbreakCrossbow {

    private final JavaPlugin plugin;

    public static final int MAX_SHOTS = 5;

    public ShieldbreakCrossbow(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack item() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;

        meta.setDisplayName("Shieldbreak Crossbow");
        meta.setLore(List.of(
                "Crossbow - Dual Hand",
                "\"Fire and you will hit something\" - Infantryman of the Ironguard",
                "Ablities:",
                "Shotgunlike - Burst: Fires many shots with high spray rate that pierce targets.",
                "Magazine: Can store up to five arrows to shoot."
        ));

        meta.setCustomModelData(3000);

        meta.getPersistentDataContainer().set(
                new NamespacedKey(plugin, "shieldbreakcrossbow"),
                PersistentDataType.BYTE,
                (byte) 1
        );

        meta.getPersistentDataContainer().set(
                ArkasteinMMO.ISSHOTGUNLIKE,
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
