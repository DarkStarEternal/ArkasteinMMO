package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class DiamondLongSwordRecipe {
    private CustomItems items;
    private Server server;

    public DiamondLongSwordRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createDiamondLongSwordRecipe() {
        ItemStack sword = items.createDiamondLongsword();

        ShapedRecipe diamondLongSword = new ShapedRecipe(sword);

        diamondLongSword.shape(" * ", " * ", " % ");

        diamondLongSword.setIngredient('*', Material.DIAMOND);
        diamondLongSword.setIngredient('%', Material.DIAMOND_SWORD);

        server.addRecipe(diamondLongSword);
    }
}