package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.entities.KhyninOverlordManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class KhyninOverlordEvents implements Listener {

    private final KhyninOverlordManager manager;

    public KhyninOverlordEvents(KhyninOverlordManager manager, JavaPlugin plugin) {
        this.manager = manager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof IronGolem overlord)) return;

        if (overlord.getPersistentDataContainer().has(manager.overlordKey, PersistentDataType.BYTE)) {
            manager.cancelOverlordTask(overlord);

            e.getDrops().clear();
            e.getEntity().getWorld().dropItemNaturally(overlord.getLocation(), new ItemStack(Material.IRON_BLOCK, 5));
        }
    }

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof IronGolem golem)) return;

        // Only apply to Khynin Overlord
        if (!golem.getPersistentDataContainer().has(
                manager.overlordKey,
                PersistentDataType.BYTE
        )) return;

        // Cancel targeting anything that is NOT a player
        if (!(event.getTarget() instanceof Player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof IronGolem golem)) return;

        if (!golem.getPersistentDataContainer().has(
                manager.overlordKey,
                PersistentDataType.BYTE
        )) return;

        if (!(event.getDamager() instanceof Player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMobTargetBoss(EntityTargetLivingEntityEvent event) {

        if (!(event.getEntity() instanceof Monster)) return;

        if (!(event.getTarget() instanceof IronGolem overlord)) return;

        if (!overlord.getPersistentDataContainer().has(
                manager.overlordKey,
                PersistentDataType.BYTE
        )) return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onMobDamageBoss(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof IronGolem overlord)) return;

        if (!overlord.getPersistentDataContainer().has(
                manager.overlordKey,
                PersistentDataType.BYTE
        )) return;

        if (!(event.getDamager() instanceof Player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onProjectileDamageBoss(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof IronGolem overlord)) return;

        if (!overlord.getPersistentDataContainer().has(
                manager.overlordKey,
                PersistentDataType.BYTE
        )) return;

        if (event.getDamager() instanceof Projectile projectile) {
            if (!(projectile.getShooter() instanceof Player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if (!(projectile instanceof Snowball)) return;

        if (!projectile.getPersistentDataContainer().has(
                manager.overlordProjectileKey,
                PersistentDataType.BYTE
        )) return;

        if (event.getHitEntity() instanceof Player player) {
            player.damage(6.0, (Entity) projectile.getShooter()); // 3 hearts
        }

        projectile.remove();
    }
}
