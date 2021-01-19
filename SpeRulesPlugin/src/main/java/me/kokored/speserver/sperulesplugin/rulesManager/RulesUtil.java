package me.kokored.speserver.sperulesplugin.rulesManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.ChatRulesGUIBuilder;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.PlayRulesGUIBuilder;
import org.bukkit.entity.Player;

public class RulesUtil {

    public static String getDate() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(now);
    }

    public static void openNewUserPlayRulesGUI(Player player) {
        player.openInventory(PlayRulesGUIBuilder.getPlayRulesGUI(player));
    }

    public static void openReadPlayRulesGUI(Player player) {
        player.openInventory(PlayRulesGUIBuilder.getReadPlayRulesGUI(player));
    }

    public static void openNewUserChatRulesGUI(Player player) {
        player.openInventory(ChatRulesGUIBuilder.getChatRulesGUI(player));
    }

    public static void openReadChatRulesGUI(Player player) {
        player.openInventory(ChatRulesGUIBuilder.getReadChatRulesGUI(player));
    }

}
