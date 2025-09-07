package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class IronLongSwordRecipe {
    private CustomItems items;
    private Server server;

    public IronLongSwordRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createIronLongSwordRecipe() {
        ItemStack sword = items.createIronLongsword();

        ShapedRecipe ironLongSword = new ShapedRecipe(sword);

        ironLongSword.shape(" * ", " * ", " % ");

        ironLongSword.setIngredient('*', Material.IRON_INGOT);
        ironLongSword.setIngredient('%', Material.IRON_SWORD);

        server.addRecipe(ironLongSword);
    }
}
