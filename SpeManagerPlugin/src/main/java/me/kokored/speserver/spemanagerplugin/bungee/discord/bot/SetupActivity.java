package me.kokored.speserver.spemanagerplugin.bungee.discord.bot;

import me.kokored.speserver.spemanagerplugin.bungee.discord.DiscordBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SetupActivity extends ListenerAdapter {

    JDA jdaApi = DiscordBot.getJDA();

    public SetupActivity() {
        jdaApi.addEventListener(this);

        setupDefault();
    }

    private void setupDefault() {
        jdaApi.getPresence().setActivity(Activity.playing("無語伺服器"));
    }

}
