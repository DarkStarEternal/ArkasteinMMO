package com.dark.arkasteinMMO.entities;

import com.dark.arkasteinMMO.items.CustomItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class CraganRiderManager {
    CustomItems cItems;
    JavaPlugin jPlugin;

    public CraganRiderManager(CustomItems items, JavaPlugin plugin) {
        cItems = items;
        jPlugin = plugin;
    }

    public void spawn(World world, org.bukkit.Location location) {
        ZombieHorse horse = world.spawn(location, ZombieHorse.class);
        horse.setAdult();
        horse.setTamed(true);
        horse.setRemoveWhenFarAway(true);
        horse.getPersistentDataContainer().set(
                new NamespacedKey(jPlugin, "cragan"),
                PersistentDataType.BYTE,
                (byte) 1
        );

        horse.getInventory().setSaddle(
                new ItemStack(Material.IRON_HORSE_ARMOR)
        );

        Zombie zombie = world.spawn(location, Zombie.class);
        zombie.setShouldBurnInDay(false);
        zombie.setCanPickupItems(true);
        zombie.setRemoveWhenFarAway(true);
        zombie.getPersistentDataContainer().set(
                new NamespacedKey(jPlugin, "cragan"),
                PersistentDataType.BYTE,
                (byte) 1
        );

        EntityEquipment eq = zombie.getEquipment();
        if (eq == null) return;

        ItemStack helmet = cItems.createCraganKnightHelmet();

        ItemStack chestplate = cItems.createCraganKnightChestplate();

        ItemStack pants = cItems.createCraganKnightPants();

        ItemStack boots = cItems.createCraganKnightBoots();

        ItemStack sword = cItems.createIronLongsword();

        ItemStack shield = new ItemStack(Material.SHIELD);

        eq.setItemInMainHand(sword);
        eq.setItemInMainHandDropChance(0.61f);

        eq.setItemInOffHand(shield);
        eq.setItemInOffHandDropChance(0.21f);

        eq.setHelmet(helmet);
        eq.setHelmetDropChance(0.41f);

        eq.setChestplate(chestplate);
        eq.setChestplateDropChance(0.35f);

        eq.setLeggings(pants);
        eq.setChestplateDropChance(0.37f);

        eq.setBoots(boots);
        eq.setChestplateDropChance(0.45f);

        horse.addPassenger(zombie);
    }
}
