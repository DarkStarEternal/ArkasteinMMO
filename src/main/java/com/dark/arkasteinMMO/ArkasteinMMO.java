package com.dark.arkasteinMMO;

import com.dark.arkasteinMMO.commands.ArkasteinMMOCommands;
import com.dark.arkasteinMMO.items.CustomItems;
import com.dark.arkasteinMMO.listeners.ItemEvents;
import com.dark.arkasteinMMO.listeners.TwoHandedItemListener;
import com.dark.arkasteinMMO.recipes.DiamondLongSwordRecipe;
import com.dark.arkasteinMMO.recipes.GoldLongSwordRecipe;
import com.dark.arkasteinMMO.recipes.IronLongSwordRecipe;
import com.dark.arkasteinMMO.recipes.NetheriteLongSwordRecipe;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;


public final class ArkasteinMMO extends JavaPlugin {

    public static NamespacedKey ISTWOHANDED;

    @Override
    public void onEnable() {
        ISTWOHANDED = new NamespacedKey(this, "istwohanded");
        // Plugin startup logic

        // Arkastein Items
        CustomItems items = new CustomItems(this);

        // Events
        Bukkit.getPluginManager().registerEvents(new ItemEvents(this), this);
        getServer().getPluginManager().registerEvents(
                new TwoHandedItemListener(this),
                this
        );

        // Commands
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            LiteralCommandNode<CommandSourceStack> node =
                    ArkasteinMMOCommands.create(items).build();
            commands.registrar().register(node);
        });

        // Recipes
        // Longswords
        new IronLongSwordRecipe(getServer(), items).createIronLongSwordRecipe();
        new GoldLongSwordRecipe(getServer(), items).createGoldLongSwordRecipe();
        new DiamondLongSwordRecipe(getServer(), items).createDiamondLongSwordRecipe();
        new NetheriteLongSwordRecipe(getServer(), items).creatNetheriteLongSwordRecipe();

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
