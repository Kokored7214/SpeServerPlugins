package me.kokored.speserver.sperulesplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class SpeRulesPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
