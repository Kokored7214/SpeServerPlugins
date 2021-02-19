package me.kokored.speserver.speentitycontrolplugin;

import me.kokored.speserver.speentitycontrolplugin.manager.Event;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeEntityControlPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        new Event();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
