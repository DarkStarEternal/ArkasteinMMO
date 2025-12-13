package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.ArkasteinMMO;
import com.dark.arkasteinMMO.items.cookingitems.BeefSoup;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomFoodListener implements Listener {

    private final JavaPlugin plugin;

    public CustomFoodListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        if (!item.hasItemMeta()) return;

        if (item.getItemMeta().getPersistentDataContainer().has(
                BeefSoup.BEEF_SOUP_KEY,
                PersistentDataType.BYTE
        )) {
            event.setCancelled(true); // prevent vanilla eating behavior

            int customHunger = item.getItemMeta().getPersistentDataContainer()
                    .getOrDefault(new NamespacedKey(plugin, "custom_hunger"), PersistentDataType.INTEGER, 6);
            double customSaturation = item.getItemMeta().getPersistentDataContainer()
                    .getOrDefault(new NamespacedKey(plugin, "custom_saturation"), PersistentDataType.DOUBLE, 7.2);

            var player = event.getPlayer();
            int newFood = Math.min(20, player.getFoodLevel() + customHunger);
            player.setFoodLevel(newFood);
            player.setSaturation((float) Math.min(20, player.getSaturation() + customSaturation));

            var inv = player.getInventory();
            item.setAmount(item.getAmount() - 1);
        }
    }
}
