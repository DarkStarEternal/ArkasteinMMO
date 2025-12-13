package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.ArkasteinMMO;
import com.dark.arkasteinMMO.items.DiamondLongsword;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class TwoHandedItemListener implements Listener {

    private final JavaPlugin plugin;

    public TwoHandedItemListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onOffhandSwap(PlayerSwapHandItemsEvent event) {
        ItemStack offhand = event.getOffHandItem();

        if (offhand == null || offhand.getType().isAir()) return;

        ItemMeta meta = offhand.getItemMeta();
        if (meta == null) return;

        if (meta.getPersistentDataContainer().has(
                ArkasteinMMO.ISTWOHANDED,
                PersistentDataType.BYTE
        )) {
            // Remove the longsword from offhand
            event.getPlayer().getInventory().setItemInOffHand(null);

            // Prevent the swap action
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
        if (mainHand == null || mainHand.getType().isAir()) return;

        ItemMeta meta = mainHand.getItemMeta();
        if (meta == null) return;

        if (meta.getPersistentDataContainer().has(
                ArkasteinMMO.ISTWOHANDED,
                PersistentDataType.BYTE
        )) {
            // Cancel offhand usage entirely
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onShieldBlock(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (mainHand == null || mainHand.getType().isAir()) return;

        ItemMeta meta = mainHand.getItemMeta();
        if (meta == null) return;

        if (meta.getPersistentDataContainer().has(
                ArkasteinMMO.ISTWOHANDED,
                PersistentDataType.BYTE
        )) {
            // Prevent shield blocking
            event.setDamage(event.getFinalDamage());
        }
    }


    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        ItemStack mainHand = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if (mainHand == null || mainHand.getType().isAir()) return;

        ItemMeta meta = mainHand.getItemMeta();
        if (meta == null) return;

        if (meta.getPersistentDataContainer().has(
                ArkasteinMMO.ISTWOHANDED,
                PersistentDataType.BYTE
        )) {
            event.getPlayer().getInventory().setItemInOffHand(null);
        }
    }
}