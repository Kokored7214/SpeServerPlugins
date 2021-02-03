package me.kokored.speserver.spemanagerplugin.bukkit.api.config;

import me.kokored.speserver.spemanagerplugin.bukkit.api.config.configs.DefaultConfig;
import org.bukkit.configuration.file.FileConfiguration;

public class Configs {

    static DefaultConfig defaultConfig = new DefaultConfig();

    public Configs() {
        defaultConfig.setup();
    }

    public static FileConfiguration get(String yml) {

        switch (yml) {

            case "config_bukkit":
                return defaultConfig.get();

        }

        return null;
    }

    public static void save(String yml) {

        switch (yml) {

            case "config_bukkit":
                defaultConfig.save();

        }

    }

    public static void reload(String yml) {

        switch (yml) {

            case "config_bukkit":
                defaultConfig.reload();

        }

    }

}
