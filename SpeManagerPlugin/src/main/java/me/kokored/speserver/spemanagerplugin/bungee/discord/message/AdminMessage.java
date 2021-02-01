package me.kokored.speserver.spemanagerplugin.bungee.discord.message;

import java.awt.Color;
import java.time.Instant;
import me.kokored.speserver.spemanagerplugin.bungee.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bungee.discord.DiscordBot;
import me.kokored.speserver.spemanagerplugin.bungee.api.MinecraftAPI;
import me.kokored.speserver.spemanagerplugin.core.util.Date;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class AdminMessage {

    static JDA jdaApi = DiscordBot.getJDA();

    static Configuration config_bungee = SpeManagerPlugin.getConfig_bungee();
    static String botChannelId = config_bungee.getString("Discord.channel.BotChannel");

    static TextChannel BotChannel = jdaApi.getTextChannelById(botChannelId);

    public static void sendFirstJoinAdminMessage(ProxiedPlayer player) {

        String name = player.getName();

        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(new Color(255, 198, 26));
        embedBuilder.setThumbnail(MinecraftAPI.getPlayerAvatars(player));

        embedBuilder.setAuthor(name, null, MinecraftAPI.getPlayerAvatars(player));

        embedBuilder.setTitle("新玩家加入了伺服器");

        embedBuilder.addField("加入日期:", Date.getDate("yyyy年 MM月 dd日 HH:mm:ss"), false);
        embedBuilder.addField("玩家ID:", player.getName(), false);
        embedBuilder.addField("UUID:", player.getUniqueId().toString(), false);

        embedBuilder.setFooter("SMPBC - 系統通知", "https://imgur.com/8bZWBFa");
        embedBuilder.setTimestamp(Instant.now());

        BotChannel.sendMessage(embedBuilder.build()).queue();

    }

    public static void sendMcLeaksMessage(ProxiedPlayer player, String kick) {

        String name = player.getName();

        EmbedBuilder embedBuilder = new EmbedBuilder();

        embedBuilder.setColor(new Color(255, 0, 0));
        embedBuilder.setThumbnail(MinecraftAPI.getPlayerAvatars(player));

        embedBuilder.setAuthor(name, null, MinecraftAPI.getPlayerAvatars(player));

        embedBuilder.setTitle("新玩家偵測到為違規賬號");

        embedBuilder.addField("違規内容:", "使用Mcleaks賬號", false);
        embedBuilder.addField("踢出玩家:", kick, false);

        embedBuilder.setFooter("SMPBC - 系統通知", "https://imgur.com/8bZWBFa");
        embedBuilder.setTimestamp(Instant.now());

        BotChannel.sendMessage(embedBuilder.build()).queue();

    }

}
