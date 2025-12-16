package com.dark.arkasteinMMO.listeners;

import com.dark.arkasteinMMO.ArkasteinMMO;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class LongBowEvents implements Listener {

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        ItemStack bow = event.getBow();
        if (bow == null || !bow.hasItemMeta()) return;

        var meta = bow.getItemMeta();
        var pdc = meta.getPersistentDataContainer();

        if (!pdc.has(ArkasteinMMO.ISLONGBOW, PersistentDataType.BYTE)) return;

        double multiplier = pdc.getOrDefault(
                ArkasteinMMO.VELOCITY_MULTIPLIER_KEY,
                PersistentDataType.DOUBLE,
                1.0
        );

        Vector velocity = event.getProjectile().getVelocity();
        event.getProjectile().setVelocity(velocity.multiply(multiplier));

        // Optional: increase arrow lifetime to avoid despawn mid-flight
        if (event.getProjectile() instanceof AbstractArrow arrow) {
            arrow.setTicksLived(1); // reset age
            arrow.setPierceLevel(arrow.getPierceLevel());
        }
    }
}
