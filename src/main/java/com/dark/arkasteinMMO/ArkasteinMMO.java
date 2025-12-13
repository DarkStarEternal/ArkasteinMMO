package com.dark.arkasteinMMO;

import com.dark.arkasteinMMO.blocks.CookingPot;
import com.dark.arkasteinMMO.blocks.CustomBlocks;
import com.dark.arkasteinMMO.commands.ArkasteinMMOCommands;
import com.dark.arkasteinMMO.entities.PortingMageManager;
import com.dark.arkasteinMMO.items.CustomItems;
import com.dark.arkasteinMMO.listeners.*;
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
    public static NamespacedKey ISCUSTOMFOOD;

    private PortingMageManager mageManager;
    CookingPot cookingPotLogic = new CookingPot(this);

    @Override
    public void onEnable() {
        ISTWOHANDED = new NamespacedKey(this, "istwohanded");
        ISCUSTOMFOOD = new NamespacedKey(this, "iscustomfood");

        // Arkastein Items and Blocks
        CustomItems items = new CustomItems(this);
        CustomBlocks blocks = new CustomBlocks(this);

        // PortingMage
        mageManager = new PortingMageManager(this, items);
        new PortingMageEvents(mageManager, this);

        // Events
        getServer().getPluginManager().registerEvents(
                new TwoHandedItemListener(this),
                this
        );
        getServer().getPluginManager().registerEvents(
                new CookingPotPlacementListener(this, cookingPotLogic),
                this
        );
        getServer().getPluginManager().registerEvents(
                new CustomFoodListener(this),
                this
        );

        // Commands
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            LiteralCommandNode<CommandSourceStack> node =
                    ArkasteinMMOCommands.create(items, mageManager).build();
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
        cookingPotLogic.saveCookingPots();
    }
}
