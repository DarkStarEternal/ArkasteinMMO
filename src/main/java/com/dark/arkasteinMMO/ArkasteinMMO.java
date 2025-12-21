package com.dark.arkasteinMMO;

import com.dark.arkasteinMMO.commands.ArkasteinMMOCommands;
import com.dark.arkasteinMMO.entities.PortingMageManager;
import com.dark.arkasteinMMO.items.CraganGladius;
import com.dark.arkasteinMMO.items.CustomItems;
import com.dark.arkasteinMMO.items.QuickFiringCrossbow;
import com.dark.arkasteinMMO.listeners.*;
import com.dark.arkasteinMMO.recipes.*;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.Name;


public final class ArkasteinMMO extends JavaPlugin {

    public static NamespacedKey ISTWOHANDED;
    public static NamespacedKey ISLONGBOW;
    public static NamespacedKey ISQUICKFIRE;
    public static NamespacedKey ISSHOTGUNLIKE;
    public static NamespacedKey STORED_SHOTS_KEY;
    public static NamespacedKey ISBURSTARROW;

    public static NamespacedKey ISCUSTOMFOOD;

    public static NamespacedKey CUSTOM_DURABILITY;
    public static NamespacedKey MAX_CUSTOM_DURABILITY;

    public static NamespacedKey MAGE_ROBE_PIECE;
    public static NamespacedKey CRAGAN_KNIGHT_PIECE;
    public static NamespacedKey CRAGAN_EXECUTIONER_PIECE;
    public static NamespacedKey ROYAL_ARMOR_PIECE;

    public static NamespacedKey VELOCITY_MULTIPLIER_KEY;


    private PortingMageManager mageManager;

    @Override
    public void onEnable() {
        ISTWOHANDED = new NamespacedKey(this, "istwohanded");
        ISLONGBOW = new NamespacedKey(this, "islongbow");
        ISCUSTOMFOOD = new NamespacedKey(this, "iscustomfood");
        ISSHOTGUNLIKE = new NamespacedKey(this, "isshotgunlike");
        ISBURSTARROW = new NamespacedKey(this, "isburstarrow");
        MAX_CUSTOM_DURABILITY = new NamespacedKey(this, "maxcustomdurability");
        CUSTOM_DURABILITY = new NamespacedKey(this, "customdurability");
        MAGE_ROBE_PIECE = new NamespacedKey(this, "magerobepiece");
        CRAGAN_KNIGHT_PIECE = new NamespacedKey(this, "craganknightpiece");
        CRAGAN_EXECUTIONER_PIECE = new NamespacedKey(this, "craganexecutionerpiece");
        ROYAL_ARMOR_PIECE = new NamespacedKey(this, "royalarmorpiece");
        VELOCITY_MULTIPLIER_KEY = new NamespacedKey(this, "longbowvelocity");
        STORED_SHOTS_KEY = new NamespacedKey(this, "stored_shots");
        ISQUICKFIRE = new NamespacedKey(this, "isquickfire");

        // Arkastein Items and Blocks
        CustomItems items = new CustomItems(this);

        // PortingMage
        mageManager = new PortingMageManager(this, items);
        new PortingMageEvents(mageManager, this);

        // Events
        getServer().getPluginManager().registerEvents(
                new TwoHandedItemListener(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new QuickFiringCrossbowEvents(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new ShieldbreakCrossbowEvents(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new MageRobeEvents(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new LongBowEvents(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new GlassBreakingListener(items),
                this
        );
        getServer().getPluginManager().registerEvents(
                new CraganExecutionerArmorEvents(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new CraganKnightArmorEvents(),
                this
        );
        getServer().getPluginManager().registerEvents(
                new RoyalArmorEvents(),
                this
        );



        // Commands
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            LiteralCommandNode<CommandSourceStack> node =
                    ArkasteinMMOCommands.create(items, mageManager, this).build();
            commands.registrar().register(node);
        });

        // Recipes
        new IronLongSwordRecipe(getServer(), items).createIronLongSwordRecipe();
        new GoldLongSwordRecipe(getServer(), items).createGoldLongSwordRecipe();
        new DiamondLongSwordRecipe(getServer(), items).createDiamondLongSwordRecipe();
        new NetheriteLongSwordRecipe(getServer(), items).creatNetheriteLongSwordRecipe();

        new CraganLongBowRecipe(getServer(), items).createCraganLongBowRecipe();
        new CraganGladiusRecipe(getServer(), items).createCraganGladiusRecipe();

        new CraganKnightBootsRecipe(getServer(), items).createCraganKnightBootsRecipe();
        new CraganKnightPantsRecipe(getServer(), items).createCraganKnightPantsRecipe();
        new CraganKnightChestplateRecipe(getServer(), items).createCraganKnightChestplateRecipe();
        new CraganKnightHelmetRecipe(getServer(), items).createCraganKnightHelmetRecipe();

        new CraganExecutionersMaskRecipe(getServer(), items).createCraganExecutionerMaskRecipe();

        new QuickFiringCrossbowRecipe(getServer(), items).createQuickFiringCrossbowRecipe();
        new ShieldbreakerCrossbowRecipe(getServer(), items).createShieldbreakerCrossbowRecipe();

        new SharpenedTwigRecipe(getServer(), items).createSharpenedTwigRecipe();
        new BambooStaffRecipe(getServer(), items).createBambooStaffRecipe();
    }

    @Override
    public void onDisable() {
        ;
    }
}
