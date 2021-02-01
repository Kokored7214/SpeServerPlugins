package me.kokored.speserver.spemanagerplugin.bukkit;

import me.kokored.speserver.spemanagerplugin.bukkit.config.Configs;
import me.kokored.speserver.spemanagerplugin.bukkit.event.player.action.DeathExp;
import me.kokored.speserver.spemanagerplugin.bukkit.sql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeManagerPlugin extends JavaPlugin {

    Configs configs;
    MySQL mySQL;

    @Override
    public void onEnable() {

        initConfigs();

        mySQL = new MySQL();

        new DeathExp();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initConfigs() {

        if (!(getDataFolder().exists())) {
            getDataFolder().mkdir();
        }

        configs = new Configs();

    }

}
