package com.dark.arkasteinMMO;

import com.dark.arkasteinMMO.items.CustomItems;
import com.dark.arkasteinMMO.items.ItemEvents;
import com.dark.arkasteinMMO.recipes.IronLongSwordRecipe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static org.bukkit.Bukkit.getServer;

public final class ArkasteinMMO extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Arkastein Items
        CustomItems items = new CustomItems(this);

        // Events
        Bukkit.getPluginManager().registerEvents(new ItemEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new CraftingEvents(this), this);

        // Recipes
        new IronLongSwordRecipe(getServer(), items).createIronLongSwordRecipe();

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
