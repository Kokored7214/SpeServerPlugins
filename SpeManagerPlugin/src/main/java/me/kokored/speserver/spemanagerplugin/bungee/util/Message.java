package me.kokored.speserver.spemanagerplugin.bungee.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Message {

    public static void consoleLog(String levels, String message) {

        String prefix = "[SpeManagerPlugin-BC] ";

        switch (levels) {
            case "info":
                ProxyServer.getInstance().getLogger().info(prefix + "[Info] " + message);
                break;
            case "warning":
                ProxyServer.getInstance().getLogger().warning(prefix + "[Warning] " + message);
                break;
            case "error":
                ProxyServer.getInstance().getLogger().warning(prefix + "[Error] " + message);
                break;
            case "sql":
                ProxyServer.getInstance().getLogger().info(prefix + "[MySQL] " + message);
                break;
            case "sql_warning":
                ProxyServer.getInstance().getLogger().warning(prefix + "[MySQL-Warning] " + message);
                break;
            case "sql_error":
                ProxyServer.getInstance().getLogger().warning(prefix + "[MySQL-Error] " + message);
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

            System.out.println(message.getText());

        }

    }

    public static String getColorText(String text) {
        String return_text = ChatColor.translateAlternateColorCodes('&', text);
        return return_text;
    }

}
