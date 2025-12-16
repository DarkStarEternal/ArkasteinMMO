package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class SharpenedTwigRecipe {
    private CustomItems items;
    private Server server;

    public SharpenedTwigRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createSharpenedTwigRecipe() {
        ItemStack twig = items.createSharpenedtwig();

        ShapelessRecipe sharpenedTwig = new ShapelessRecipe(twig);

        sharpenedTwig.addIngredient(Material.STICK);

        server.addRecipe(sharpenedTwig);
    }
}