package com.dark.arkasteinMMO.entities;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PortingMageManager {

    private final JavaPlugin plugin;
    public final NamespacedKey portingMageKey;
    private final CustomItems items;

    public PortingMageManager(JavaPlugin plugin, CustomItems items) {
        this.plugin = plugin;
        this.items = items;
        this.portingMageKey = new NamespacedKey(plugin, "porting_mage");
    }


    public void spawnPortingMage(Player player) {
        WanderingTrader mage = player.getWorld().spawn(player.getLocation(), WanderingTrader.class);

        mage.getPersistentDataContainer().set(portingMageKey, PersistentDataType.BYTE, (byte) 1);

        // Remove llamas if any
        mage.getNearbyEntities(5,5,5).stream()
                .filter(e -> e.getType() == org.bukkit.entity.EntityType.LLAMA)
                .forEach(org.bukkit.entity.Entity::remove);

        mage.setRecipes(getCustomTrades());

        // Schedule despawn after 5 minutes (6000 ticks)
        Bukkit.getScheduler().runTaskLater(plugin, mage::remove, 6000L);
    }

    private List<MerchantRecipe> getCustomTrades() {
        List<MerchantRecipe> trades = new ArrayList<>();

        ItemStack emeralds1 = new ItemStack(Material.EMERALD, 38);
        ItemStack shatteredstaff = items.createShatteredStaff();
        MerchantRecipe recipe1 = new MerchantRecipe(shatteredstaff, 1);
        recipe1.addIngredient(emeralds1);
        trades.add(recipe1);


        ItemStack emeralds2 = new ItemStack(Material.EMERALD, 64);
        ItemStack blazerod1 = new ItemStack(Material.BLAZE_ROD, 4);
        ItemStack flamingsledgehammer = items.createFlamingSledgehammer();
        MerchantRecipe recipe2 = new MerchantRecipe(flamingsledgehammer, 1);
        recipe2.addIngredient(emeralds2);
        recipe2.addIngredient(blazerod1);
        trades.add(recipe2);

        return trades;
    }
}
