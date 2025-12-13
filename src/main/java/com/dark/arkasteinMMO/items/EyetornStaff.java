package com.dark.arkasteinMMO.items;

import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class EyetornStaff {
    JavaPlugin plugin;

    public EyetornStaff(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack EyetornStaffItem() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("Eyetorn Staff");
            meta.setLore(List.of(
                    "Staff - Dual hand",
                    "The eye torn out of a giants skull, now enhancing spells cast by this bloody weapon",
                    "Abilities:"
            ));
            meta.setCustomModelData(2);
            meta.getPersistentDataContainer().set(
                    ArkasteinMMO.ISTWOHANDED,
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

}
