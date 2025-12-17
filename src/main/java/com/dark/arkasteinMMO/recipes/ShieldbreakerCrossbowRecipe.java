package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ShieldbreakerCrossbowRecipe {
    private CustomItems items;
    private Server server;

    public ShieldbreakerCrossbowRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createShieldbreakerCrossbowRecipe() {
        ItemStack crossbow = items.createQuickFireCrossbow();

        ShapedRecipe shieldbreakerCrossbow = new ShapedRecipe(crossbow);

        shieldbreakerCrossbow.shape("-* ", "#% ", "#-*");

        shieldbreakerCrossbow.setIngredient('*', Material.BREEZE_ROD);
        shieldbreakerCrossbow.setIngredient('%', Material.CROSSBOW);
        shieldbreakerCrossbow.setIngredient('-', Material.TRIPWIRE_HOOK);
        shieldbreakerCrossbow.setIngredient('#', Material.STRING);

        server.addRecipe(shieldbreakerCrossbow);
    }
}