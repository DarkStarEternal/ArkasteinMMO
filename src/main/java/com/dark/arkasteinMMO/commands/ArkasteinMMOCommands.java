package com.dark.arkasteinMMO.commands;

import com.dark.arkasteinMMO.entities.KhyninOverlordManager;
import com.dark.arkasteinMMO.entities.PortingMageManager;
import com.dark.arkasteinMMO.items.CustomItems;
import com.dark.arkasteinMMO.listeners.KhyninOverlordEvents;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ArkasteinMMOCommands {

    public static LiteralArgumentBuilder<CommandSourceStack> create(CustomItems customItems, PortingMageManager mageManager, JavaPlugin plugin) {
        return Commands.literal("arkastein")
                .then(
                        Commands.literal("give")
                                .then(
                                        Commands.argument("item", StringArgumentType.word())
                                                .suggests((ctx, builder) -> {
                                                    customItems.keys().forEach(builder::suggest);
                                                    return builder.buildFuture();
                                                })
                                                .executes(ctx -> {
                                                    CommandSourceStack source = ctx.getSource();

                                                    if (!(source.getExecutor() instanceof Player player)) {
                                                        source.getSender().sendMessage(Component.text("Only players can use this command."));
                                                        return 1;
                                                    }

                                                    String itemName = StringArgumentType.getString(ctx, "item");
                                                    ItemStack item = customItems.get(itemName);

                                                    if (item == null) {
                                                        player.sendMessage(Component.text("Unknown item: " + itemName));
                                                        return 1;
                                                    }

                                                    player.getInventory().addItem(item);
                                                    player.sendMessage(Component.text("You received: " + itemName));
                                                    return 1;
                                                })
                                )
                                .build()
                )
                .then(
                        Commands.literal("spawnmage")
                                .executes(ctx -> {
                                    CommandSourceStack source = ctx.getSource();
                                    if (!(source.getExecutor() instanceof Player player)) {
                                        source.getSender().sendMessage(Component.text("Only players can use this command."));
                                        return 1;
                                    }

                                    // Spawn the Porting Mage
                                    mageManager.spawnPortingMage(player);
                                    player.sendMessage(Component.text("A Porting Mage has appeared!"));
                                    return 1;
                                })
                )
                // Inside the create() method
                .then(
                        Commands.literal("spawnoverlord")
                                .executes(ctx -> {
                                    CommandSourceStack source = ctx.getSource();
                                    if (!(source.getExecutor() instanceof Player player)) {
                                        source.getSender().sendMessage(Component.text("Only players can use this command."));
                                        return 1;
                                    }

                                    // Spawn the overlord
                                    KhyninOverlordManager overlordManager = new KhyninOverlordManager(plugin);
                                    new KhyninOverlordEvents(overlordManager, plugin);
                                    overlordManager.spawnOverlord(player);
                                    return 1;
                                })
                );



    }
}