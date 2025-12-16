package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraganLongBowRecipe {
    private CustomItems items;
    private Server server;

    public CraganLongBowRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createCraganLongBowRecipe() {
        ItemStack bow = items.createCraganLongBow();

        ShapedRecipe craganLongBow = new ShapedRecipe(bow);

        craganLongBow.shape("** ", " % ", " **");

        craganLongBow.setIngredient('*', Material.STICK);
        craganLongBow.setIngredient('%', Material.BOW);

        server.addRecipe(craganLongBow);
    }
}