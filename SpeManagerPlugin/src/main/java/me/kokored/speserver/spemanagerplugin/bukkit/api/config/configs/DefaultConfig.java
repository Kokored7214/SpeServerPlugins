package me.kokored.speserver.spemanagerplugin.bukkit.api.config.configs;

import java.io.File;
import java.io.IOException;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class DefaultConfig {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    private File file;
    private FileConfiguration configuration;

    public void setup() {
        file = new File(plugin.getDataFolder(), "config_bukkit.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                plugin.saveResource("config_bukkit.yml", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration get() {
        return configuration;
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Couldn't save config.yml file!");
        }
    }

    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }

}
