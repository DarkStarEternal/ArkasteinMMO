package com.dark.arkasteinMMO.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CustomItems {
    private final JavaPlugin plugin;

    // Registry: key = item name, value = item factory
    private final Map<String, Supplier<ItemStack>> registry = new HashMap<>();

    public CustomItems(JavaPlugin plugin) {
        this.plugin = plugin;

        // Register all your items once here
        registry.put("iron_longsword", this::createIronLongsword);
        registry.put("gold_longsword", this::createGoldLongsword);
        registry.put("diamond_longsword", this::createDiamondLongsword);
        registry.put("netherite_longsword", this::createNetheriteLongsword);
        registry.put("flaming_sledgehammer", this::createFlamingSledgehammer);
        registry.put("sharpened_twig", this::createSharpenedtwig);
        registry.put("glass_shank", this::createGlassShank);
        registry.put("shattered_staff", this::createShatteredStaff);
        registry.put("eyetorn_staff", this::createEyetornStaff);
    }

    // Public lookup method
    public ItemStack get(String key) {
        Supplier<ItemStack> factory = registry.get(key.toLowerCase());
        return factory != null ? factory.get() : null;
    }

    // Optionally, expose all keys for tab completion
    public Iterable<String> keys() {
        return registry.keySet();
    }

    // Your existing creators
    public ItemStack createIronLongsword() { return new IronLongsword(plugin).IronLongswordItem(); }
    public ItemStack createGoldLongsword() { return new GoldLongsword(plugin).GoldLongSwordItem(); }
    public ItemStack createDiamondLongsword() { return new DiamondLongsword(plugin).DiamondLongSwordItem(); }
    public ItemStack createNetheriteLongsword() { return new NetheriteLongsword(plugin).NetheriteLongSwordItem(); }
    public ItemStack createFlamingSledgehammer() { return new FlamingSledgehammer(plugin).FlamnigSledgehammerItem(); }
    public ItemStack createSharpenedtwig() { return new Sharpenedtwig(plugin).SharpenedtwigItem(); }
    public ItemStack createGlassShank() { return new GlassShank(plugin).GlassShankItem(); }
    public ItemStack createShatteredStaff() { return new ShatteredStaff(plugin).ShatteredStaffItem(); }
    public ItemStack createEyetornStaff() { return new EyetornStaff(plugin).EyetornStaffItem(); }
}