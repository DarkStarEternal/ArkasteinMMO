package com.dark.arkasteinMMO.commands;

import com.dark.arkasteinMMO.items.CustomItems;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArkasteinMMOCommands {

    public static LiteralArgumentBuilder<CommandSourceStack> create(CustomItems customItems) {
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
                );
    }
}