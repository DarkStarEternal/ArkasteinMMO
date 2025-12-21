package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.persistence.PersistentDataType;

public class RoyalArmorEvents implements Listener {

    @EventHandler
    public void onArmorDamage(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;

        var meta = item.getItemMeta();
        var pdc = meta.getPersistentDataContainer();

        if (!pdc.has(ArkasteinMMO.ROYAL_ARMOR_PIECE, PersistentDataType.BYTE)) return;

        event.setCancelled(true); // stop vanilla durability

        int durability = pdc.get(ArkasteinMMO.CUSTOM_DURABILITY, PersistentDataType.INTEGER);
        int max = pdc.get(ArkasteinMMO.MAX_CUSTOM_DURABILITY, PersistentDataType.INTEGER);

        durability -= event.getDamage();

        if (durability <= 0) {
            Player player = event.getPlayer();
            player.getInventory().remove(item);
            return;
        }

        pdc.set(ArkasteinMMO.CUSTOM_DURABILITY, PersistentDataType.INTEGER, durability);

        if (meta instanceof Damageable dmg) {
            int vanillaMax = item.getType().getMaxDurability();
            int visualDamage = vanillaMax - (int) ((durability / (double) max) * vanillaMax);
            dmg.setDamage(Math.max(0, visualDamage));
        }

        item.setItemMeta(meta);
    }
}
