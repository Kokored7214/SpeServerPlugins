package me.kokored.speserver.sperulesplugin.rulesManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.PlayRules.GUIBuilder;
import org.bukkit.entity.Player;

public class rulesUtil {

    public static String getDate() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(now);
    }

    public static void openNewUserPlayRulesGUI(Player player) {
        player.openInventory(GUIBuilder.getPlayRulesGUI(player));
    }

    public static void openReadPlayRulesGUI(Player player) {
        player.openInventory(GUIBuilder.getReadPlayRulesGUI(player));
    }

}
