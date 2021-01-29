package me.kokored.speserver.spemanagerplugin.bungee.util;

import me.kokored.speserver.spemanagerplugin.bungee.SpeManagerPlugin;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Message {

    public static void consoleLog(String levels, String message) {

        String prefix = "[SpeManagerPlugin-BC] ";

        switch (levels) {
            case "info":
                System.out.println(prefix + "[Info] " + message);
                break;
            case "warning":
                System.out.println(prefix + "[Warning] " + message);
                break;
            case "error":
                System.out.println(prefix + "[Error] " + message);
                break;
            case "sql_info":
                System.out.println(prefix + "[MySQL-Info] " + message);
                break;
            case "sql_warning":
                System.out.println(prefix + "[MySQL-Warning] " + message);
                break;
            case "sql_error":
                System.out.println(prefix + "[MySQL-Error] " + message);
                break;
        }

    }

    public static void sendAdminMessage(String message) {

        for (ProxiedPlayer online_players : ProxyServer.getInstance().getPlayers()) {

            if (online_players.hasPermission("spe.managerplugin.admin")) {

                ProxiedPlayer admin = online_players;

                admin.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));

            }

        }

    }

    public static void sendGlobalMessage(String message) {

        for (ProxiedPlayer online_players : ProxyServer.getInstance().getPlayers()) {

            online_players.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));

            System.out.println(message);

        }

    }

    public static void sendGlobalMessage(TextComponent message) {

        for (ProxiedPlayer online_players : ProxyServer.getInstance().getPlayers()) {

            online_players.sendMessage(message);

            System.out.println(message.toString());

        }

    }

    public static String getColorText(String text) {
        String return_text = ChatColor.translateAlternateColorCodes('&', text);
        return return_text;
    }

}
