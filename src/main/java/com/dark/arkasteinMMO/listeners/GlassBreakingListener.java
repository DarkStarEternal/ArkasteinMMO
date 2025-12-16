package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class GlassBreakingListener implements Listener {

    CustomItems items;

    private final Random random = new Random();

    // All vanilla glass blocks and panes + tinted glass
    private final List<Material> glassBlocks = List.of(
            Material.GLASS,
            Material.GLASS_PANE,
            Material.TINTED_GLASS,
            Material.WHITE_STAINED_GLASS,
            Material.ORANGE_STAINED_GLASS,
            Material.MAGENTA_STAINED_GLASS,
            Material.LIGHT_BLUE_STAINED_GLASS,
            Material.YELLOW_STAINED_GLASS,
            Material.LIME_STAINED_GLASS,
            Material.PINK_STAINED_GLASS,
            Material.GRAY_STAINED_GLASS,
            Material.LIGHT_GRAY_STAINED_GLASS,
            Material.CYAN_STAINED_GLASS,
            Material.PURPLE_STAINED_GLASS,
            Material.BLUE_STAINED_GLASS,
            Material.BROWN_STAINED_GLASS,
            Material.GREEN_STAINED_GLASS,
            Material.RED_STAINED_GLASS,
            Material.BLACK_STAINED_GLASS,
            Material.WHITE_STAINED_GLASS_PANE,
            Material.ORANGE_STAINED_GLASS_PANE,
            Material.MAGENTA_STAINED_GLASS_PANE,
            Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Material.YELLOW_STAINED_GLASS_PANE,
            Material.LIME_STAINED_GLASS_PANE,
            Material.PINK_STAINED_GLASS_PANE,
            Material.GRAY_STAINED_GLASS_PANE,
            Material.LIGHT_GRAY_STAINED_GLASS_PANE,
            Material.CYAN_STAINED_GLASS_PANE,
            Material.PURPLE_STAINED_GLASS_PANE,
            Material.BLUE_STAINED_GLASS_PANE,
            Material.BROWN_STAINED_GLASS_PANE,
            Material.GREEN_STAINED_GLASS_PANE,
            Material.RED_STAINED_GLASS_PANE,
            Material.BLACK_STAINED_GLASS_PANE
    );

    public GlassBreakingListener(CustomItems items) {
        this.items = items;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!glassBlocks.contains(event.getBlock().getType())) return;

        Player player = event.getPlayer();

        // Prevent vanilla drops
        event.setDropItems(false);

        ItemStack handItem = player.getInventory().getItemInMainHand();

        // Silk Touch: drop vanilla block
        if (handItem.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
            event.getBlock().getWorld().dropItemNaturally(
                    event.getBlock().getLocation(),
                    new ItemStack(event.getBlock().getType())
            );
            return;
        }

        // Otherwise: drop shards
        ItemStack shards = new ItemStack(Material.AIR);
        int minAmount = 0;
        int maxAmount = 1;
        int amount = minAmount + random.nextInt(maxAmount - minAmount + 1);

        if (random.nextInt(maxAmount - minAmount + 1) == 1) {
            shards = items.createGlassShank();
        }
        else {
            shards = new ItemStack(Material.AIR);
        }
        event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), shards);
    }
}