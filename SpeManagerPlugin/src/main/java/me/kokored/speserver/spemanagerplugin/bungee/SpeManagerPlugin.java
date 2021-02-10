package me.kokored.speserver.spemanagerplugin.bungee;

import com.google.common.io.ByteStreams;
import java.io.*;
import me.kokored.speserver.spemanagerplugin.bungee.discord.DiscordBot;
import me.kokored.speserver.spemanagerplugin.bungee.api.sql.MySQL;
import me.kokored.speserver.spemanagerplugin.bungee.system.SQLPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.bukkit.Material;

public class SpeManagerPlugin extends Plugin {

    static Plugin plugin;
    static ProxyServer proxyServer;
    static Configuration config_bungee;

    DiscordBot discordBot;
    MySQL mySQL;

    @Override
    public void onEnable() {

        createDefaultConfig();

        plugin = this;
        proxyServer = getProxy();

        discordBot = new DiscordBot();
        mySQL = new MySQL();

        new SQLPlayer();
        
    }

    @Override
    public void onDisable() {

        mySQL.disconnect();

    }

    boolean createDefaultConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }
            File config = new File(getDataFolder().getPath(), "config_bungee.yml");
            if (!config.exists()) {
                try {
                    config.createNewFile();
                    try (InputStream is = getResourceAsStream("config_bungee.yml");
                         OutputStream os = new FileOutputStream(config)) {
                        ByteStreams.copy(is, os);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Unable to create configuration file", e);
                }
            }
            this.config_bungee = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        saveDefaultConfig();
        return true;
    }

    void saveDefaultConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config_bungee,
                    new File(getDataFolder(), "config_bungee.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static ProxyServer getProxyServer() {
        return proxyServer;
    }

    public static Configuration getConfig_bungee() {
        return config_bungee;
    }

}
