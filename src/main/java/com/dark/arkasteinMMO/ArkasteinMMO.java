package com.dark.arkasteinMMO;

import com.dark.arkasteinMMO.items.CustomItems;
import com.dark.arkasteinMMO.items.ItemEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArkasteinMMO extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Arkastein Items
        CustomItems items = new CustomItems(this);
        ItemStack longsword = items.createIronLongsword();

        // Item Events
        Bukkit.getPluginManager().registerEvents(new ItemEvents(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
