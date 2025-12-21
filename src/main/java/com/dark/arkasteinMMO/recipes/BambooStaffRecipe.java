package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class BambooStaffRecipe {
    private CustomItems items;
    private Server server;

    public BambooStaffRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createBambooStaffRecipe() {
        ItemStack sword = items.createBambooStaff();

        ShapedRecipe recipe = new ShapedRecipe(sword);

        recipe.shape("   ", " * ", " * ");

        recipe.setIngredient('*', Material.BAMBOO);

        server.addRecipe(recipe);
    }
}
