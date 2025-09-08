package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class NetheriteLongSwordRecipe {
    private CustomItems items;
    private Server server;

    public NetheriteLongSwordRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void creatNetheriteLongSwordRecipe() {
        ItemStack sword = items.createNetheriteLongsword();

        ShapedRecipe netheriteLongSword = new ShapedRecipe(sword);

        netheriteLongSword.shape(" * ", " * ", " % ");

        netheriteLongSword.setIngredient('*', Material.NETHERITE_INGOT);
        netheriteLongSword.setIngredient('%', Material.NETHERITE_SWORD);

        server.addRecipe(netheriteLongSword);
    }
}