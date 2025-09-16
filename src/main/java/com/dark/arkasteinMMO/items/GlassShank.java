package com.dark.arkasteinMMO.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;

public class GlassShank {
    JavaPlugin plugin;

    public GlassShank(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack GlassShankItem() {
        ItemStack item = new ItemStack(Material.STONE_AXE);
        ItemMeta meta = item.getItemMeta();

        AttributeModifier damageModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.attackDamage",
                3,
                AttributeModifier.Operation.ADD_NUMBER
        );
        AttributeModifier speedModifier = new AttributeModifier(
                UUID.randomUUID(),
                "generic.attackSpeed",
                1.7,
                AttributeModifier.Operation.ADD_NUMBER
        );

        if (meta != null) {
            meta.setDisplayName("Glass Shank");
            meta.setLore(List.of(
                    "Improvised small weapon",
                    "Everything can be used as a weapon, even shattered pieces of glass.",
                    "Abilities:",
                    "Cracked: May cause bleed damage to hit enemies at the cost of five durablity."
            ));
            meta.setCustomModelData(1);
            meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, damageModifier);
            meta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
            meta.getPersistentDataContainer().set(
                    new NamespacedKey(plugin, "glassshank"),
                    PersistentDataType.BYTE,
                    (byte) 1
            );
            item.setItemMeta(meta);
        }
        return item;
    }

}
