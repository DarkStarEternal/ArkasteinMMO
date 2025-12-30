package com.dark.arkasteinMMO.entities;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class CraganKnightManager {
    CustomItems cItems;
    JavaPlugin jPlugin;

    public CraganKnightManager(CustomItems items, JavaPlugin plugin) {
        cItems = items;
        jPlugin = plugin;
    }

    public Zombie spawn(World world, org.bukkit.Location location) {
        Zombie zombie = world.spawn(location, Zombie.class);

        zombie.setShouldBurnInDay(false);
        zombie.setCanPickupItems(true);
        zombie.setRemoveWhenFarAway(true);
        zombie.getPersistentDataContainer().set(
                new NamespacedKey(jPlugin, "cragan"),
                PersistentDataType.BYTE,
                (byte) 1
        );

        equip(zombie);
        return zombie;
    }

    private void equip(Zombie zombie) {
        EntityEquipment eq = zombie.getEquipment();
        if (eq == null) return;

        Random random = new Random();

        ItemStack helmet = new ItemStack(Material.AIR);
        if (random.nextInt(100) > 60) {
            helmet = cItems.createCraganKnightHelmet();
        }
        ItemStack chestplate = cItems.createCraganKnightChestplate();
        ItemStack pants = new ItemStack(Material.AIR);
        if (random.nextInt(100) > 20) {
            pants = cItems.createCraganKnightPants();
        }

        ItemStack boots = new ItemStack(Material.AIR);
        if (random.nextInt(100) > 9) {
            boots = cItems.createCraganKnightBoots();
        }

        ItemStack mainHand = new ItemStack(Material.AIR);
        if (random.nextInt(100) > 30) {
            if (random.nextInt(100) > 12) {
                mainHand = cItems.createCraganGladius();
            }
            else {
                mainHand = cItems.createCraganLongBow();
            }
        }

        ItemStack shield = new ItemStack(Material.AIR);
        if (mainHand != cItems.createCraganLongBow()) {
            if (random.nextInt(100) > 42) {
                shield = new ItemStack(Material.SHIELD);
            }
        }

        eq.setItemInOffHand(shield);
        eq.setItemInOffHandDropChance(0.61f);

        eq.setItemInMainHand(mainHand);
        eq.setItemInMainHandDropChance(0.31f);

        eq.setHelmet(helmet);
        eq.setHelmetDropChance(0.41f);

        eq.setChestplate(chestplate);
        eq.setChestplateDropChance(0.35f);

        eq.setLeggings(pants);
        eq.setChestplateDropChance(0.37f);

        eq.setBoots(boots);
        eq.setChestplateDropChance(0.45f);
    }
}
