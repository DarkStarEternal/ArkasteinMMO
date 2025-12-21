package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraganKnightHelmetRecipe {
    private CustomItems items;
    private Server server;

    public CraganKnightHelmetRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createCraganKnightHelmetRecipe() {
        ItemStack sword = items.createCraganKnightHelmet();

        ShapedRecipe recipe = new ShapedRecipe(sword);

        recipe.shape(" # ", " * ", " % ");

        recipe.setIngredient('*', Material.IRON_HELMET);
        recipe.setIngredient('#', Material.CHAINMAIL_HELMET);
        recipe.setIngredient('%', Material.LEATHER);

        server.addRecipe(recipe);
    }
}
