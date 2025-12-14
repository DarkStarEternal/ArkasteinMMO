package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class TwoHandedItemListener implements Listener {

    private boolean isTwoHanded(ItemStack item) {
        if (item == null || item.getType().isAir()) return false;
        if (!item.hasItemMeta()) return false;
        return item.getItemMeta().getPersistentDataContainer()
                .has(ArkasteinMMO.ISTWOHANDED, org.bukkit.persistence.PersistentDataType.BYTE);
    }

    private void handleOffhand(Player player) {
        ItemStack offhand = player.getInventory().getItemInOffHand();
        if (offhand != null && !offhand.getType().isAir()) {
            int emptySlot = player.getInventory().firstEmpty();
            if (emptySlot != -1) {
                player.getInventory().setItem(emptySlot, offhand);
            } else {
                player.getWorld().dropItemNaturally(player.getLocation(), offhand);
            }
            player.getInventory().setItemInOffHand(null);
        }
    }

    @EventHandler
    public void onSwapHand(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (isTwoHanded(player.getInventory().getItemInMainHand())) {
            event.setCancelled(true); // block swap
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() == EquipmentSlot.OFF_HAND && isTwoHanded(player.getInventory().getItemInMainHand())) {
            event.setCancelled(true); // block offhand usage
        }
    }

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack newMainHand = player.getInventory().getItem(event.getNewSlot());
        if (isTwoHanded(newMainHand)) {
            handleOffhand(player);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (isTwoHanded(mainHand)) {
            handleOffhand(player);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (isTwoHanded(mainHand)) {
            ItemStack offhand = player.getInventory().getItemInOffHand();
            if (offhand != null && offhand.getType() == Material.SHIELD) {
                handleOffhand(player); // move or drop shield
            }
        }
    }
}
