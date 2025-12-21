package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraganGladiusRecipe {
    private CustomItems items;
    private Server server;

    public CraganGladiusRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createCraganGladiusRecipe() {
        ItemStack sword = items.createCraganGladius();

        ShapedRecipe craganGladius = new ShapedRecipe(sword);

        craganGladius.shape(" # ", " * ", " % ");

        craganGladius.setIngredient('*', Material.IRON_INGOT);
        craganGladius.setIngredient('#', Material.IRON_NUGGET);
        craganGladius.setIngredient('%', Material.STICK);

        server.addRecipe(craganGladius);
    }
}
