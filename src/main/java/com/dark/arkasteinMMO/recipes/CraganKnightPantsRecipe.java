package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraganKnightPantsRecipe {
    private CustomItems items;
    private Server server;

    public CraganKnightPantsRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createCraganKnightPantsRecipe() {
        ItemStack sword = items.createCraganKnightPants();

        ShapedRecipe recipe = new ShapedRecipe(sword);

        recipe.shape(" # ", " * ", " % ");

        recipe.setIngredient('*', Material.CHAINMAIL_LEGGINGS);
        recipe.setIngredient('#', Material.IRON_INGOT);
        recipe.setIngredient('%', Material.LEATHER);

        server.addRecipe(recipe);
    }
}
