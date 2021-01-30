package me.kokored.speserver.spemanagerplugin.bungee.discord;

import javax.security.auth.login.LoginException;
import me.kokored.speserver.spemanagerplugin.bungee.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bungee.discord.bot.SetupActivity;
import me.kokored.speserver.spemanagerplugin.bungee.util.Message;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.config.Configuration;

public class DiscordBot extends ListenerAdapter implements EventListener {

    static JDA jdaApi;

    static Configuration config_bungee = SpeManagerPlugin.getConfig_bungee();
    String token = config_bungee.getString("Discord.token");

    public DiscordBot() {

        if (token.equals("BOT TOKEN HERE")) {

            Message.consoleLog("warning", "Discord bot token was not set up.");

            return;
        }

        try {
            jdaApi = JDABuilder.createDefault(token)
                    .addEventListeners(this).build();
            jdaApi.awaitReady();

            //bot
            new SetupActivity();

        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static JDA getJDA() {
        return jdaApi;
    }

}
