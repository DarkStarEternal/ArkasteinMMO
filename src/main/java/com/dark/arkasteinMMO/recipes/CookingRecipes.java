package com.dark.arkasteinMMO.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CookingRecipes {
    public static List<CookingRecipe> getRecipes() {
        return List.of(
                new CookingRecipe(
                        Arrays.asList(Material.POTATO),
                        new ItemStack(Material.BAKED_POTATO),
                        100 // 5 seconds
                ),
                new CookingRecipe(
                        Arrays.asList(Material.BEEF),
                        new ItemStack(Material.COOKED_BEEF),
                        120 // 6 seconds
                )
        );
    }
}
