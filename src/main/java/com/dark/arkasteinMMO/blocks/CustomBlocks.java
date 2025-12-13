package com.dark.arkasteinMMO.blocks;

import com.dark.arkasteinMMO.items.CookingPotItem;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CustomBlocks {

    private final Map<String, Supplier<Object>> registry = new HashMap<>();
    private final JavaPlugin plugin;

    public CustomBlocks(JavaPlugin plugin) {
        this.plugin = plugin;

        registry.put("cooking_pot", () -> new CookingPot(plugin));
    }

    public Object get(String key) {
        Supplier<Object> factory = registry.get(key.toLowerCase());
        return factory != null ? factory.get() : null;
    }

    public Iterable<String> keys() {
        return registry.keySet();
    }

    public CookingPotItem getCookingPotItem() {
        return new CookingPotItem(plugin);
    }
}
