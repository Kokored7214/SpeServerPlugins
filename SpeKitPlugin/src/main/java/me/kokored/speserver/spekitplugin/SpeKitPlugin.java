package me.kokored.speserver.spekitplugin;

import me.kokored.speserver.spekitplugin.kit.Manager;
import me.kokored.speserver.spekitplugin.sql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeKitPlugin extends JavaPlugin {

    MySQL mySQL;

    @Override
    public void onEnable() {

        /*
        Plugin depend = Bukkit.getPluginManager().getPlugin("SpeManagerPlugin");

        if (depend == null) {
            getLogger().warning("SpeManagerPlugin was not found!!!");
            getLogger().info("Plugin disable...");

            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        */

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        new Manager();
        mySQL = new MySQL();


    }

    @Override
    public void onDisable() {

    }
}
