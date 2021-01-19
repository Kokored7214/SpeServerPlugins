package me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI;

import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;
import me.kokored.speserver.sperulesplugin.rulesManager.RulesUtil;
import me.kokored.speserver.sperulesplugin.sql.MySqlAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class PlayRulesGUI implements CommandExecutor, Listener {

    Plugin plugin = SpeRulesPlugin.getPlugin(SpeRulesPlugin.class);

    public PlayRulesGUI() {
        Bukkit.getPluginCommand("rules").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (MySqlAPI.playRulesConfirmed(player.getUniqueId().toString()) == false) {
            RulesUtil.openNewUserPlayRulesGUI(player);
        }else {
            RulesUtil.openReadPlayRulesGUI(player);
        }

        return false;
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (MySqlAPI.playRulesConfirmed(player.getUniqueId().toString()) == false) {
            event.setCancelled(true);
            RulesUtil.openNewUserPlayRulesGUI(player);
        }
    }
    @EventHandler
    public void onClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (MySqlAPI.playRulesConfirmed(player.getUniqueId().toString()) == false) {
            event.setCancelled(true);
            RulesUtil.openNewUserPlayRulesGUI(player);
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (MySqlAPI.playRulesConfirmed(player.getUniqueId().toString()) == false) {
            event.setCancelled(true);
        }
    }

}
