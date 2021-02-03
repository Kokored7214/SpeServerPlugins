package me.kokored.speserver.spemanagerplugin.bukkit.util;

import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Message {

    static Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    public static void consoleLog(String levels, String message) {

        switch (levels) {
            case "info":
                plugin.getLogger().info("[Info] " + message);
                break;
            case "warning":
                plugin.getLogger().warning("[Warning] " + message);
                break;
            case "error":
                plugin.getLogger().warning("[Error] " + message);
                break;
            case "sql":
                plugin.getLogger().info("[MySQL] " + message);
                break;
            case "sql_warning":
                plugin.getLogger().warning("[MySQL-Warning] " + message);
                break;
            case "sql_error":
                plugin.getLogger().warning("[MySQL-Error] " + message);
                break;
        }

    }

    public static void sendAdminMessage(String message) {

        for (Player online_players : Bukkit.getOnlinePlayers()) {

            if (online_players.hasPermission("spe.managerplugin.admin")) {

                Player admin = online_players;

                admin.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

            }

        }

    }

    public static void sendGlobalMessage(String message) {

        for (Player online_players : Bukkit.getOnlinePlayers()) {

            online_players.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

            System.out.println(message);

        }

    }

    public static void sendGlobalMessage(TextComponent message) {

        for (Player online_players : Bukkit.getOnlinePlayers()) {

            online_players.spigot().sendMessage(message);

            System.out.println(message);

        }

    }

    public static String getColorText(String text) {
        String return_text = ChatColor.translateAlternateColorCodes('&', text);
        return return_text;
    }

    public static String getBooleanText(Boolean booleanToChange) {
        if (booleanToChange == true) {
            return getColorText("&a是");
        }else {
            return getColorText("&c否");
        }
    }

}
