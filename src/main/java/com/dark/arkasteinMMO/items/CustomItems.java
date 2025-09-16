package com.dark.arkasteinMMO.items;

import com.dark.arkasteinMMO.recipes.IronLongSwordRecipe;
import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static org.bukkit.attribute.Attribute.ATTACK_DAMAGE;

public class CustomItems {
    private final JavaPlugin plugin;

    public CustomItems(JavaPlugin plugin)
    {
        this.plugin = plugin;
    }

    public ItemStack createIronLongsword() {
        return new IronLongsword(plugin).IronLongswordItem();
    }
    public ItemStack createGoldLongsword() {
        return new GoldLongsword(plugin).GoldLongSwordItem();
    }
    public ItemStack createDiamondLongsword() {
        return new DiamondLongsword(plugin).DiamondLongSwordItem();
    }
    public ItemStack createNetheriteLongsword() {
        return new NetheriteLongsword(plugin).NetheriteLongSwordItem();
    }

    public ItemStack createFlamingSledgehammer() {
        return new FlamingSledgehammer(plugin).FlamnigSledgehammerItem();
    }

    public ItemStack createSharpenedtwig() {
        return new Sharpenedtwig(plugin).SharpenedtwigItem();
    }
    public ItemStack createGlassShank() {
        return new GlassShank(plugin).GlassShankItem();
    }

    public ItemStack createShatteredStaff() {
        return new ShatteredStaff(plugin).ShatteredStaffItem();
    }
}