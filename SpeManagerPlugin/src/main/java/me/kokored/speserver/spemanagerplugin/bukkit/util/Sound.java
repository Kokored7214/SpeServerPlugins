package me.kokored.speserver.spemanagerplugin.bukkit.util;

import org.bukkit.entity.Player;

public class Sound {

    public static void playGUIClickSound(Player player) {
        org.bukkit.Sound sound = org.bukkit.Sound.UI_BUTTON_CLICK;
        player.playSound(player.getLocation(), sound, 1f, 1f);
    }

}
