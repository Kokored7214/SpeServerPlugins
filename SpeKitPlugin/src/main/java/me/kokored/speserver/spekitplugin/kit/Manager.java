package me.kokored.speserver.spekitplugin.kit;

import java.util.List;
import me.kokored.speserver.spekitplugin.SpeKitPlugin;
import me.kokored.speserver.spekitplugin.sql.MySQLAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class Manager implements Listener {

    static Plugin plugin = SpeKitPlugin.getPlugin(SpeKitPlugin.class);

    private static Boolean serverType = plugin.getConfig().getBoolean("Lobby");

    public Manager() {

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (MySQLAPI.hasPlayer(player.getUniqueId().toString()) == false) {
            return;
        }

    }

}
