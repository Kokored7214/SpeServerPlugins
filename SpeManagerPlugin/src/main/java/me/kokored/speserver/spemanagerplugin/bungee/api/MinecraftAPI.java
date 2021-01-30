package me.kokored.speserver.spemanagerplugin.bungee.api;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MinecraftAPI {

    public static String getPlayerAvatars(String uuid) {
        String return_text = "https://crafatar.com/avatars/" + uuid.replace("-", "") + ".png?overlay";
        return return_text;
    }

    public static String getPlayerAvatars(ProxiedPlayer player) {
        String return_text = "https://crafatar.com/avatars/" + player.getUniqueId().toString().replace("-", "") + ".png?overlay";
        return return_text;
    }

}
