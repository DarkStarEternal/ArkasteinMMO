package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class GoldLongSwordRecipe {
    private CustomItems items;
    private Server server;

    public GoldLongSwordRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createGoldLongSwordRecipe() {
        ItemStack sword = items.createGoldLongsword();

        ShapedRecipe goldLongSword = new ShapedRecipe(sword);

        goldLongSword.shape(" * ", " * ", " % ");

        goldLongSword.setIngredient('*', Material.GOLD_INGOT);
        goldLongSword.setIngredient('%', Material.GOLDEN_SWORD);

        server.addRecipe(goldLongSword);
    }
}
