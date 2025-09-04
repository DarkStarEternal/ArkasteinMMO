package com.dark.arkasteinMMO;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.EnchantmentKeys;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

public class ArkasteinMMOBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(BootstrapContext context) {
    }

    @Override
    public JavaPlugin createPlugin(PluginProviderContext context) {
        return new ArkasteinMMO();
    }
}
