package me.kokored.speserver.spemanagerplugin.bungee.discord.bot;

import me.kokored.speserver.spemanagerplugin.bungee.discord.DiscordBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

public class SetupActivity {

    JDA jdaApi = DiscordBot.getJDA();

    public SetupActivity() {

        jdaApi.getPresence().setActivity(Activity.playing("無語伺服器"));

    }

}
