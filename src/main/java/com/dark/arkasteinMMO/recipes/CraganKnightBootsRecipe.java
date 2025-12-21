package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraganKnightBootsRecipe {
    private CustomItems items;
    private Server server;

    public CraganKnightBootsRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createCraganKnightBootsRecipe() {
        ItemStack sword = items.createCraganKnightBoots();

        ShapedRecipe recipe = new ShapedRecipe(sword);

        recipe.shape("   ", " * ", " % ");

        recipe.setIngredient('*', Material.IRON_BOOTS);
        recipe.setIngredient('%', Material.LEATHER);

        server.addRecipe(recipe);
    }
}
