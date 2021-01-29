package me.kokored.speserver.spemanagerplugin.bungee.system;

import me.kokored.speserver.spemanagerplugin.bungee.sql.MySQL;
import me.kokored.speserver.spemanagerplugin.bungee.util.Date;
import me.kokored.speserver.spemanagerplugin.bungee.util.ErrorCode;
import me.kokored.speserver.spemanagerplugin.bungee.util.Message;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class SQLPlayer implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {

        ProxiedPlayer player = event.getPlayer();
        String name = player.getName();
        String uuid = player.getUniqueId().toString();

        if (MySQL.getDbStats() == false) {
            Message.consoleLog("sql_error", "error code: " + ErrorCode.sql_100());

            player.disconnect(new ComponentBuilder()
                    .append("在連綫至伺服器時發生錯誤, 請向伺服器技術人員回報此錯誤!").color(ChatColor.RED)
                    .append("\n")
                    .append("\n")
                    .append("錯誤代碼: ").color(ChatColor.WHITE).append(ErrorCode.sql_100()).color(ChatColor.GRAY)
                    .append("\n")
                    .append("時間: ").color(ChatColor.WHITE).append(Date.getDate()).color(ChatColor.GRAY)
                    .append("\n")
                    .append("玩家: ").color(ChatColor.WHITE).append(name).color(ChatColor.GRAY)
                    .create());

            return;
        }

        if (MySQL.hasPlayer(uuid)) {

            MySQL.updatePlayerName(player);

            return;
        }

        MySQL.addPlayer(uuid, name, Date.getDate(), "new_player," + Date.getDate());

    }

}
