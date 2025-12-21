package com.dark.arkasteinMMO.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CustomItems {
    private final JavaPlugin plugin;

    private final Map<String, Supplier<ItemStack>> registry = new HashMap<>();

    public CustomItems(JavaPlugin plugin) {
        this.plugin = plugin;

        registry.put("iron_longsword", this::createIronLongsword);
        registry.put("gold_longsword", this::createGoldLongsword);
        registry.put("diamond_longsword", this::createDiamondLongsword);
        registry.put("netherite_longsword", this::createNetheriteLongsword);

        registry.put("flaming_sledgehammer", this::createFlamingSledgehammer);

        registry.put("sharpened_twig", this::createSharpenedtwig);
        registry.put("glass_shank", this::createGlassShank);
        registry.put("bamboo_staff", this::createBambooStaff);

        registry.put("shattered_staff", this::createShatteredStaff);
        registry.put("eyetorn_staff", this::createEyetornStaff);

        registry.put("cragan_longbow", this::createCraganLongBow);
        registry.put("cragan_gladius", this::createCraganGladius);
        registry.put("executioners_hellbarde", this::createExecutionersHellbarde);

        registry.put("quickfire_crossbow", this::createQuickFireCrossbow);
        registry.put("shieldbreaker_crossbow", this::createShieldbreakerCrossbow);

        registry.put("mage_hood", this::createMageHood);
        registry.put("mage_robe", this::createMageRobe);
        registry.put("mage_pants", this::createMagePants);

        registry.put("cragan_executioners_mask", this::createCraganExecutionerMask);

        registry.put("cragan_knight_helmet", this::createCraganKnightHelmet);
        registry.put("cragan_knight_chestplate", this::createCraganKnightChestplate);
        registry.put("cragan_knight_pants", this::createCraganKnightPants);
        registry.put("cragan_knight_boots", this::createCraganKnightBoots);

        registry.put("royal_helmet", this::createRoyalHelmet);
        registry.put("royal_chestplate", this::createRoyalChestplate);
        registry.put("royal_pants", this::createRoyalPants);
        registry.put("royal_boots", this::createRoyalBoots);

        registry.put("royal_zweihander", this::createRoyalZweihander);
    }

    public ItemStack get(String key) {
        Supplier<ItemStack> factory = registry.get(key.toLowerCase());
        return factory != null ? factory.get() : null;
    }

    public Iterable<String> keys() {
        return registry.keySet();
    }

    public ItemStack createIronLongsword() { return new IronLongsword(plugin).IronLongswordItem(); }
    public ItemStack createGoldLongsword() { return new GoldLongsword(plugin).GoldLongSwordItem(); }
    public ItemStack createDiamondLongsword() { return new DiamondLongsword(plugin).getItem(); }
    public ItemStack createNetheriteLongsword() { return new NetheriteLongsword(plugin).NetheriteLongSwordItem(); }

    public ItemStack createFlamingSledgehammer() { return new FlamingSledgehammer(plugin).FlamnigSledgehammerItem(); }

    public ItemStack createSharpenedtwig() { return new Sharpenedtwig(plugin).SharpenedtwigItem(); }
    public ItemStack createGlassShank() { return new GlassShank(plugin).GlassShankItem(); }
    public ItemStack createBambooStaff() { return new BambooPole(plugin).Item(); }

    public ItemStack createShatteredStaff() { return new ShatteredStaff(plugin).ShatteredStaffItem(); }
    public ItemStack createEyetornStaff() { return new EyetornStaff(plugin).EyetornStaffItem(); }

    public ItemStack createMageHood() { return new MageHood(plugin).MageHoodItem(); }
    public ItemStack createMageRobe() { return new MageRobe(plugin).MageRobeItem(); }
    public ItemStack createMagePants() { return new MagePants(plugin).MagePantsItem(); }

    public ItemStack createCraganLongBow() { return new CraganLongBow(plugin).CraganLongBowItem(); }
    public ItemStack createCraganGladius() { return new CraganGladius(plugin).Item(); }
    public ItemStack createExecutionersHellbarde() { return new ExecutionersHellbarde(plugin).Item(); }
    public ItemStack createCraganKnightHelmet() { return new CraganKnightHelmet(plugin).Item(); }
    public ItemStack createCraganKnightChestplate() { return new CraganKnightChestplate(plugin).Item(); }
    public ItemStack createCraganKnightPants() { return new CraganKnightPants(plugin).Item(); }
    public ItemStack createCraganKnightBoots() { return new CraganKnightBoots(plugin).Item(); }
    public ItemStack createCraganExecutionerMask() { return new CraganExecutionerMask(plugin).Item(); }

    public ItemStack createRoyalHelmet() { return new RoyalHelmet(plugin).Item(); }
    public ItemStack createRoyalChestplate() { return new RoyalChestplate(plugin).Item(); }
    public ItemStack createRoyalPants() { return new RoyalPants(plugin).Item(); }
    public ItemStack createRoyalBoots() { return new RoyalBoots(plugin).Item(); }

    public ItemStack createRoyalZweihander() { return new RoyalGuardsZweihander(plugin).getItem(); }

    public ItemStack createQuickFireCrossbow() { return new QuickFiringCrossbow(plugin).item(); }
    public ItemStack createShieldbreakerCrossbow() { return new ShieldbreakCrossbow(plugin).item(); }
}