package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.blocks.CookingPot;
import com.dark.arkasteinMMO.items.CookingPotItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CookingPotPlacementListener implements Listener {

    private final JavaPlugin plugin;
    private final CookingPot cookingPot;

    public CookingPotPlacementListener(JavaPlugin plugin, CookingPot cookingPot) {
        this.plugin = plugin;
        this.cookingPot = cookingPot;
    }

    @EventHandler
    public void onPlace(PlayerInteractEvent event) {
        // Only handle right-click on block
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getHand() != EquipmentSlot.HAND) return; // main hand only
        Player player = event.getPlayer();

        ItemStack item = event.getItem();
        if (item == null) return;

        if (!item.getItemMeta().getPersistentDataContainer()
                .has(CookingPotItem.COOKING_POT_KEY, org.bukkit.persistence.PersistentDataType.BYTE))
            return;

        event.setCancelled(true);

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;

        Block targetBlock = clickedBlock.getRelative(event.getBlockFace());

        if (targetBlock.getType() != Material.AIR) return;

        // Place cauldron
        targetBlock.setType(Material.CAULDRON);

        cookingPot.registerCookingPot(targetBlock);

        // Remove one item from the player’s hand
        int amount = item.getAmount();
        if (amount > 1) item.setAmount(amount - 1);
        else player.getInventory().remove(item);
    }
}
