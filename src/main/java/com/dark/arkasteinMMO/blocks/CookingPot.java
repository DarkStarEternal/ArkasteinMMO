package com.dark.arkasteinMMO.blocks;

import com.dark.arkasteinMMO.items.CookingPotItem;
import com.dark.arkasteinMMO.recipes.CookingRecipe;
import com.dark.arkasteinMMO.recipes.CookingRecipes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CookingPot implements Listener {

    private final JavaPlugin plugin;
    private final Set<Block> cookingPots = new HashSet<>();
    private final Map<Block, PotData> potDataMap = new HashMap<>();
    private final List<CookingRecipe> recipes;

    private final File dataFile;
    private final YamlConfiguration dataConfig;

    public CookingPot(JavaPlugin plugin) {
        this.plugin = plugin;
        this.recipes = CookingRecipes.getRecipes();

        this.dataFile = new File(plugin.getDataFolder(), "cookingpots.yml");
        this.dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        loadCookingPots();
    }

    private static class PotData {
        int progress;
        List<ItemStack> input = new ArrayList<>(Arrays.asList(null, null, null));
        ItemStack fuel;
        ItemStack output;
    }

    public void registerCookingPot(Block block) {
        cookingPots.add(block);
        potDataMap.putIfAbsent(block, new PotData());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        ItemStack item = event.getItemInHand();
        if (block.getType() != Material.CAULDRON) return;

        if (item != null && item.getItemMeta() != null &&
                item.getItemMeta().getPersistentDataContainer()
                        .has(CookingPotItem.COOKING_POT_KEY, org.bukkit.persistence.PersistentDataType.BYTE)) {
            registerCookingPot(block);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        Block block = event.getClickedBlock();
        if (!cookingPots.contains(block)) return;

        event.setCancelled(true);
        Player player = event.getPlayer();
        Inventory gui = createCookingPotGUI(block);
        player.openInventory(gui);
    }

    public Inventory createCookingPotGUI(Block block) {
        Inventory gui = Bukkit.createInventory(null, 9, "§6Cooking Pot");
        PotData data = potDataMap.get(block);
        if (data != null) {
            for (int i = 0; i < data.input.size(); i++) gui.setItem(i, data.input.get(i));
            gui.setItem(3, data.fuel);
            gui.setItem(4, data.output);
        }
        startCooking(block, gui);
        return gui;
    }

    public void startCooking(Block block, Inventory gui) {
        PotData data = potDataMap.get(block);
        if (data == null) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack fuel = gui.getItem(3);
                if (fuel == null || fuel.getType() != Material.COAL) return;

                data.progress++;
                updateProgressBar(gui, data.progress);

                for (CookingRecipe recipe : recipes) {
                    if (recipe.matches(Arrays.asList(gui.getItem(0), gui.getItem(1), gui.getItem(2)))) {
                        if (data.progress >= recipe.getCookTime()) {
                            gui.setItem(4, recipe.getResult());
                            for (int i = 0; i < 3; i++) gui.setItem(i, null);
                            fuel.setAmount(fuel.getAmount() - 1);
                            if (fuel.getAmount() <= 0) gui.setItem(3, null);
                            data.progress = 0;
                        }
                    }
                }

                // Save inventory state
                data.input.set(0, gui.getItem(0));
                data.input.set(1, gui.getItem(1));
                data.input.set(2, gui.getItem(2));
                data.fuel = gui.getItem(3);
                data.output = gui.getItem(4);
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private void updateProgressBar(Inventory gui, int progress) {
        int percentage = Math.min(100, progress);
        Material color;
        if (percentage < 25) color = Material.RED_WOOL;
        else if (percentage < 50) color = Material.ORANGE_WOOL;
        else if (percentage < 75) color = Material.YELLOW_WOOL;
        else color = Material.LIME_WOOL;

        gui.setItem(5, new ItemStack(color));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§6Cooking Pot")) return;
        event.setCancelled(true);
    }

    public void saveCookingPots() {
        dataConfig.set("pots", null); // clear old data
        int index = 0;
        for (Block block : cookingPots) {
            PotData data = potDataMap.get(block);
            if (data == null) continue;

            String path = "pots." + index;
            dataConfig.set(path + ".world", block.getWorld().getName());
            dataConfig.set(path + ".x", block.getX());
            dataConfig.set(path + ".y", block.getY());
            dataConfig.set(path + ".z", block.getZ());
            dataConfig.set(path + ".progress", data.progress);

            for (int i = 0; i < 3; i++) {
                if (data.input.get(i) != null) dataConfig.set(path + ".input." + i, data.input.get(i));
            }
            if (data.fuel != null) dataConfig.set(path + ".fuel", data.fuel);
            if (data.output != null) dataConfig.set(path + ".output", data.output);

            index++;
        }

        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCookingPots() {
        if (!dataFile.exists()) return;

        try {
            dataConfig.load(dataFile);
            if (!dataConfig.isConfigurationSection("pots")) return;

            for (String key : dataConfig.getConfigurationSection("pots").getKeys(false)) {
                String path = "pots." + key;
                String worldName = dataConfig.getString(path + ".world");
                World world = Bukkit.getWorld(worldName);
                if (world == null) continue;

                int x = dataConfig.getInt(path + ".x");
                int y = dataConfig.getInt(path + ".y");
                int z = dataConfig.getInt(path + ".z");
                Block block = world.getBlockAt(x, y, z);

                PotData data = new PotData();
                data.progress = dataConfig.getInt(path + ".progress");

                for (int i = 0; i < 3; i++) {
                    if (dataConfig.contains(path + ".input." + i))
                        data.input.set(i, dataConfig.getItemStack(path + ".input." + i));
                }
                if (dataConfig.contains(path + ".fuel"))
                    data.fuel = dataConfig.getItemStack(path + ".fuel");
                if (dataConfig.contains(path + ".output"))
                    data.output = dataConfig.getItemStack(path + ".output");

                cookingPots.add(block);
                potDataMap.put(block, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
