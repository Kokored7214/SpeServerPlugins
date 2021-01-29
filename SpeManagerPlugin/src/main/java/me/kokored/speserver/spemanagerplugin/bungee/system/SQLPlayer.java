package me.kokored.speserver.spemanagerplugin.bungee.system;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.spemanagerplugin.bungee.discord.player.JoinQuit;
import me.kokored.speserver.spemanagerplugin.bungee.sql.MySQL;
import me.kokored.speserver.spemanagerplugin.bungee.util.Date;
import me.kokored.speserver.spemanagerplugin.bungee.util.ErrorCode;
import me.kokored.speserver.spemanagerplugin.bungee.util.Message;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class SQLPlayer implements Listener {

    List<ProxiedPlayer> preLoginList = new ArrayList<>();
    List<ProxiedPlayer> playingList = new ArrayList<>();

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {

        ProxiedPlayer player = event.getPlayer();
        String name = player.getName();

        if (MySQL.getDbStats() == false) {
            Message.consoleLog("sql_error", "error code: " + ErrorCode.getErrorCode("no_sql_connect"));

            player.disconnect(new ComponentBuilder()
                    .append("在連綫至伺服器時發生錯誤, 請向伺服器技術人員回報此錯誤!").color(ChatColor.RED)
                    .append("\n")
                    .append("\n")
                    .append("錯誤代碼: ").color(ChatColor.WHITE).append(ErrorCode.getErrorCode("no_sql_connect")).color(ChatColor.GRAY)
                    .append("\n")
                    .append("時間: ").color(ChatColor.WHITE).append(Date.getDate()).color(ChatColor.GRAY)
                    .append("\n")
                    .append("玩家: ").color(ChatColor.WHITE).append(name).color(ChatColor.GRAY)
                    .create());

            return;
        }

        preLoginList.add(player);

    }

    @EventHandler
    public void onServerConnect(ServerConnectedEvent event) {

        ProxiedPlayer player = event.getPlayer();
        String name = player.getName();
        String uuid = player.getUniqueId().toString();

        if (preLoginList.contains(player) && !(playingList.contains(player))) {

            if (!(MySQL.hasPlayer(uuid))) {

                MySQL.addPlayer(uuid, name, Date.getDate(), "new_player," + Date.getDate());

                sendFirstJoinMessage(player);
                JoinQuit.sendFirstJoinMessage(player);

            }

            MySQL.updatePlayerName(player);

            sendJoinMessage(player);
            JoinQuit.sendJoinMessage(player);

            playingList.add(player);
            preLoginList.remove(player);

        }else {

            Message.sendAdminMessage(Message.getColorText("&f[&c系統&f] &7» &f玩家 &e" + name + " &f前往了伺服器 &d" + player.getServer().getInfo().getName()));

        }

    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {

        ProxiedPlayer player = event.getPlayer();

        if (playingList.contains(player)) {

            sendQuitMessage(player);
            JoinQuit.sendQuitMessage(player);

            playingList.remove(player);

        }

    }

    private void sendFirstJoinMessage(ProxiedPlayer player) {

        TextComponent msg = new TextComponent(Message.getColorText("&6✦ &b歡迎新玩家 &e" + player.getName() + " &b加入無語伺服器!"));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder()
                .append("加入日期: ").color(ChatColor.WHITE)
                .append(Date.getDate()).color(ChatColor.GRAY)
                .create()));

        Message.sendGlobalMessage(msg);

    }

    private void sendJoinMessage(ProxiedPlayer player) {

        TextComponent msg = new TextComponent(Message.getColorText("&a▲ &e" + player.getName() + " &d上綫了"));

        Message.sendGlobalMessage(msg);

    }

    private void sendQuitMessage(ProxiedPlayer player) {

        TextComponent msg = new TextComponent(Message.getColorText("&c▼ &e" + player.getName() + " &d下綫了"));

        Message.sendGlobalMessage(msg);

    }

}
