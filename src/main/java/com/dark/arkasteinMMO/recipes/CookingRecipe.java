package com.dark.arkasteinMMO.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CookingRecipe {
    private final List<Material> ingredients;
    private final ItemStack result;
    private final int cookTime; // in ticks

    public CookingRecipe(List<Material> ingredients, ItemStack result, int cookTime) {
        this.ingredients = ingredients;
        this.result = result;
        this.cookTime = cookTime;
    }

    public List<Material> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result;
    }

    public int getCookTime() {
        return cookTime;
    }

    public boolean matches(List<ItemStack> inputItems) {
        if (inputItems.size() != ingredients.size()) return false;
        for (int i = 0; i < ingredients.size(); i++) {
            ItemStack stack = inputItems.get(i);
            if (stack == null || stack.getType() != ingredients.get(i)) return false;
        }
        return true;
    }
}
