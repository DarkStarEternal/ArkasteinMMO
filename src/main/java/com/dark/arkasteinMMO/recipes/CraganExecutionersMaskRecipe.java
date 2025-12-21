package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraganExecutionersMaskRecipe {
    private CustomItems items;
    private Server server;

    public CraganExecutionersMaskRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createCraganExecutionerMaskRecipe() {
        ItemStack sword = items.createCraganExecutionerMask();

        ShapedRecipe recipe = new ShapedRecipe(sword);

        recipe.shape(" # ", " * ", " % ");

        recipe.setIngredient('*', Material.IRON_INGOT);
        recipe.setIngredient('#', Material.IRON_HELMET);
        recipe.setIngredient('%', Material.LEATHER);

        server.addRecipe(recipe);
    }
}
