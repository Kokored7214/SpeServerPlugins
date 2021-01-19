package me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.PlayRules;

import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;
import me.kokored.speserver.sperulesplugin.sql.MySqlAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class GUI implements CommandExecutor, Listener {

    Plugin plugin = SpeRulesPlugin.getPlugin(SpeRulesPlugin.class);

    public GUI() {
        Bukkit.getPluginCommand("rules").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        openNewUserPlayRulesGUI(player);
        player.sendMessage("open");

        return false;
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (MySqlAPI.playRulesConfirmed(player.getUniqueId().toString()) == false) {
            openNewUserPlayRulesGUI(player);
            player.sendMessage("open");
        }
    }

    public void openNewUserPlayRulesGUI(Player player) {
        Inventory inventory = GUIBuilder.getPlayRulesGUI(player);
        player.openInventory(inventory);
    }

}
