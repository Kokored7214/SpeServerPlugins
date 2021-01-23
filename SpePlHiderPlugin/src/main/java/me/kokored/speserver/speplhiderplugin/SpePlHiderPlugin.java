package me.kokored.speserver.speplhiderplugin;

import me.kokored.speserver.speplhiderplugin.manager.PlHider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpePlHiderPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        new PlHider();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
