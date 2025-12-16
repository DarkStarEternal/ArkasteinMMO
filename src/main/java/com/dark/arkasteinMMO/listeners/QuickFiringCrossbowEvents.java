package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.ArkasteinMMO;
import com.dark.arkasteinMMO.items.QuickFiringCrossbow;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class QuickFiringCrossbowEvents implements Listener {

    JavaPlugin plugin;
    /* ===========================
       LOADING MECHANIC
       Sneak + Right Click
       =========================== */
    public QuickFiringCrossbowEvents(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLoad(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        Player player = event.getPlayer();
        if (!player.isSneaking()) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != org.bukkit.Material.CROSSBOW || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        var pdc = meta.getPersistentDataContainer();

        if (!pdc.has(ArkasteinMMO.ISQUICKFIRE, PersistentDataType.BYTE)) return;

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
       BURST FIRE
       =========================== */
    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack bow = event.getBow();
        if (bow == null || !bow.hasItemMeta()) return;

        ItemMeta meta = bow.getItemMeta();
        var pdc = meta.getPersistentDataContainer();

        if (!pdc.has(ArkasteinMMO.ISQUICKFIRE, PersistentDataType.BYTE)) return;

        int shots = pdc.getOrDefault(
                ArkasteinMMO.STORED_SHOTS_KEY,
                PersistentDataType.INTEGER,
                0
        );

        if (shots <= 0) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(true); // cancel vanilla shot
        Vector baseVelocity = event.getProjectile().getVelocity();

        for (int i = 0; i < shots; i++) {
            int delay = i * 6; // 2 ticks between arrows

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                AbstractArrow arrow = player.launchProjectile(
                        (Class<? extends AbstractArrow>) event.getProjectile().getClass()
                );

                // Slight spread per arrow
                Vector spread = baseVelocity.clone().add(new Vector(
                        randomSpread(),
                        randomSpread(),
                        randomSpread()
                ));

                arrow.setVelocity(spread.multiply(1.2));
                arrow.setShooter(player);
                arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);

                // Optional: play shoot sound per arrow
                player.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 1f, 0.8f);

            }, delay);
        }

        // Reset stored shots immediately to prevent re-firing mid-burst
        pdc.set(
                ArkasteinMMO.STORED_SHOTS_KEY,
                PersistentDataType.INTEGER,
                0
        );
        bow.setItemMeta(meta);
    }

    private double randomSpread() {
        return (Math.random() - 0.5) * 0.08;
    }
}
