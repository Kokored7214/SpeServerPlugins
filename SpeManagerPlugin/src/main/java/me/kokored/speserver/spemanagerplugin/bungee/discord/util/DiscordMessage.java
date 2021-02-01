package me.kokored.speserver.spemanagerplugin.bungee.discord.util;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class DiscordMessage {

    public static String getDiscordName(ProxiedPlayer player) {
        String name = player.getName();
        String return_text = name.replaceAll("[_]", "\\\\_");
        return return_text;
    }

    public static String getDiscordName(String name) {
        String return_text = name.replaceAll("[_]", "\\\\_");
        return return_text;
    }

    public static String getBooleanText(Boolean booleanToChange) {
        if (booleanToChange == true) {
            return "是";
        }else {
            return "否";
        }
    }

}
