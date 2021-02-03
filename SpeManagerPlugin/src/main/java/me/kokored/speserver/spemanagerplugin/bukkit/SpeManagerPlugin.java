package me.kokored.speserver.spemanagerplugin.bukkit;

import me.kokored.speserver.spemanagerplugin.bukkit.api.VaultAPI;
import me.kokored.speserver.spemanagerplugin.bukkit.api.config.Configs;
import me.kokored.speserver.spemanagerplugin.bukkit.feature.inventory.GUIHandler;
import me.kokored.speserver.spemanagerplugin.bukkit.feature.game.DeathExp;
import me.kokored.speserver.spemanagerplugin.bukkit.api.sql.MySQL;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeManagerPlugin extends JavaPlugin {

    Configs configs;

    VaultAPI vaultAPI;

    MySQL mySQL;

    @Override
    public void onEnable() {

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            Message.consoleLog("warning", "[API] Plugin Vault was not found! Pls install this plugin!");
            return;
        }

        init();

        new DeathExp();
        new GUIHandler();

    }

    @Override
    public void onDisable() {

        mySQL.disconnect();

    }

    private void init() {

        if (!(getDataFolder().exists())) {
            getDataFolder().mkdir();
        }

        configs = new Configs();

        vaultAPI = new VaultAPI();
        mySQL = new MySQL();

    }

}
