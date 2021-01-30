package me.kokored.speserver.spemanagerplugin.bungee.discord.message;

import java.awt.Color;
import me.kokored.speserver.spemanagerplugin.bungee.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bungee.discord.DiscordBot;
import me.kokored.speserver.spemanagerplugin.bungee.discord.util.DiscordMessage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class LoginMessage {

    static JDA jdaApi = DiscordBot.getJDA();

    static Configuration config_bungee = SpeManagerPlugin.getConfig_bungee();
    static String chatChannelId = config_bungee.getString("Discord.channel.ChatChannel");

    static TextChannel ChatChannel = jdaApi.getTextChannelById(chatChannelId);

    public static void sendFirstJoinMessage(ProxiedPlayer player) {

        String name = player.getName();

        EmbedBuilder firstJoinEmbed = new EmbedBuilder();

        firstJoinEmbed.setColor(new Color(255, 198, 26));

        firstJoinEmbed.setAuthor("歡迎新玩家 " + name + " 加入無語伺服器!", null,
                "https://crafatar.com/avatars/" + player.getUniqueId().toString().replace("-", "") + ".png?overlay");

        ChatChannel.sendMessage(firstJoinEmbed.build()).queue();

    }

    public static void sendJoinMessage(ProxiedPlayer player) {

        int online = ProxyServer.getInstance().getOnlineCount();

        String name = DiscordMessage.getDiscordName(player);
        String text = ":inbox_tray:  **" + name + " 上綫了**";

        jdaApi.getPresence().setActivity(Activity.watching(online + " 位玩家正在游玩"));

        ChatChannel.sendMessage(text).queue();

    }

    public static void sendQuitMessage(ProxiedPlayer player) {

        int online = ProxyServer.getInstance().getOnlineCount() - 1;

        String name = DiscordMessage.getDiscordName(player);
        String text = ":outbox_tray:  **" + name + " 下綫了**";

        if (online > 0) {
            jdaApi.getPresence().setActivity(Activity.watching(online + " 位玩家正在游玩"));
        }else if (online == 0) {
            jdaApi.getPresence().setActivity(Activity.watching("沒有玩家在綫"));
        }

        ChatChannel.sendMessage(text).queue();

    }

}
