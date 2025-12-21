package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.ArkasteinMMO;
import com.dark.arkasteinMMO.items.QuickFiringCrossbow;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class ShieldbreakCrossbowEvents implements Listener {

    /* ===========================
       LOADING MECHANIC
       Sneak + Right Click
       =========================== */
    @EventHandler
    public void onLoad(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        Player player = event.getPlayer();
        if (!player.isSneaking()) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != org.bukkit.Material.CROSSBOW || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        var pdc = meta.getPersistentDataContainer();

        if (!pdc.has(ArkasteinMMO.ISSHOTGUNLIKE, PersistentDataType.BYTE)) return;

        int shots = pdc.getOrDefault(
                ArkasteinMMO.STORED_SHOTS_KEY,
                PersistentDataType.INTEGER,
                0
        );

        if (shots >= QuickFiringCrossbow.MAX_SHOTS) return;

        pdc.set(
                ArkasteinMMO.STORED_SHOTS_KEY,
                PersistentDataType.INTEGER,
                shots + 1
        );

        item.setItemMeta(meta);
        player.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_LOADING_END, 1f, 1.2f);
        event.setCancelled(true);
    }

    /* ===========================
       INSTANT BURST FIRE
       =========================== */
    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack bow = event.getBow();
        if (bow == null || !bow.hasItemMeta()) return;

        ItemMeta meta = bow.getItemMeta();
        var pdc = meta.getPersistentDataContainer();

        if (!pdc.has(ArkasteinMMO.ISSHOTGUNLIKE, PersistentDataType.BYTE)) return;

        int shots = pdc.getOrDefault(
                ArkasteinMMO.STORED_SHOTS_KEY,
                PersistentDataType.INTEGER,
                0
        );

        if (shots <= 0) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(true);

        Vector baseVelocity = event.getProjectile().getVelocity();

        for (int i = 0; i < shots; i++) {
            AbstractArrow arrow = player.launchProjectile(
                    (Class<? extends AbstractArrow>) event.getProjectile().getClass()
            );

            Vector spread = baseVelocity.clone().add(new Vector(
                    randomSpread1(),
                    randomSpread2(),
                    randomSpread3()
            ));

            arrow.setVelocity(spread.multiply(2.6));
            arrow.setShooter(player);
            arrow.setPierceLevel(1);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);

            // 🔑 Mark arrow as burst arrow
            arrow.getPersistentDataContainer().set(
                    ArkasteinMMO.ISBURSTARROW,
                    PersistentDataType.BYTE,
                    (byte) 1
            );
        }

        // Reset stored shots immediately
        pdc.set(
                ArkasteinMMO.STORED_SHOTS_KEY,
                PersistentDataType.INTEGER,
                0
        );
        bow.setItemMeta(meta);

        player.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1f, 0.9f);
    }

    /* ===========================
       BURST DAMAGE OVERRIDE
       =========================== */
    @EventHandler(ignoreCancelled = true)
    public void onBurstArrowHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getShooter() instanceof Player shooter)) return;
        if (!(event.getEntity() instanceof LivingEntity target)) return;

        var pdc = arrow.getPersistentDataContainer();

        if (!pdc.has(ArkasteinMMO.ISBURSTARROW, PersistentDataType.BYTE)) return;

        // Cancel vanilla damage
        event.setCancelled(true);

        // Reset invulnerability frames
        target.setNoDamageTicks(0);

        // Apply manual damage (still respects armor & enchants)
        target.damage(arrow.getDamage(), shooter);

        // Prevent double hits
        arrow.remove();
    }

    private double randomSpread1() {
        return (Math.random() - 0.5) * 0.08;
    }
    private double randomSpread2() {
        return (Math.random() + 0.5) * 0.008;
    }
    private double randomSpread3() {
        return (Math.random() - 0.7) * 0.14;
    }
}
