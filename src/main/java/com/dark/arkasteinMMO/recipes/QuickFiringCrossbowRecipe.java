package com.dark.arkasteinMMO.recipes;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class QuickFiringCrossbowRecipe {
    private CustomItems items;
    private Server server;

    public QuickFiringCrossbowRecipe(Server server, CustomItems items) {
        this.items = items;
        this.server = server;
    }

    public void createQuickFiringCrossbowRecipe() {
        ItemStack crossbow = items.createQuickFireCrossbow();

        ShapedRecipe quickFiringCrossbow = new ShapedRecipe(crossbow);

        quickFiringCrossbow.shape("-* ", "#% ", "#-*");

        quickFiringCrossbow.setIngredient('*', Material.STICK);
        quickFiringCrossbow.setIngredient('%', Material.CROSSBOW);
        quickFiringCrossbow.setIngredient('-', Material.TRIPWIRE_HOOK);
        quickFiringCrossbow.setIngredient('#', Material.STRING);

        server.addRecipe(quickFiringCrossbow);
    }
}