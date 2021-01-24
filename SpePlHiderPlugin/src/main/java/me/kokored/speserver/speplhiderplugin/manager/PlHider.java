package me.kokored.speserver.speplhiderplugin.manager;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.speplhiderplugin.SpePlHiderPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.Plugin;

public class PlHider implements Listener {

    Plugin plugin = SpePlHiderPlugin.getPlugin(SpePlHiderPlugin.class);

    List<String> targetCommands = new ArrayList<>();

    public PlHider() {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        targetCommands.add("/minecraft:help");

        targetCommands.add("/bukkit:plugins");
        targetCommands.add("/bukkit:pl");
        targetCommands.add("/plugins");
        targetCommands.add("/pl");

        targetCommands.add("/bukkit:version");
        targetCommands.add("/bukkit:ver");
        targetCommands.add("/version");
        targetCommands.add("/ver");

        targetCommands.add("/bukkit:help");
        targetCommands.add("/bukkit:?");
        targetCommands.add("/help");
        targetCommands.add("/?");

    }

    @EventHandler
    public void onExecuteCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();

        for (int i = 0 ; i < targetCommands.size() ; i++){

            if (command.contains(targetCommands.get(i))  && !(player.hasPermission("speserver.plhiderplugin.checkbypass"))) {

                if (!(event.isCancelled())) {
                    event.setCancelled(true);
                }

                player.sendMessage(ChatColor.RED + "十分抱歉, 你無法使用這個指令.");
                player.sendMessage(ChatColor.YELLOW + "如需幫助, 可聯係在綫之伺服器管理員.");

                plugin.getLogger().info("[Manager] Command \"" + command + "\" send by player " + player.getName() + " has been blocked!");

                for (Player online : Bukkit.getOnlinePlayers()) {

                    if (online.hasPermission("speserver.plhiderplugin.warnmsg")) {

                        Player admin = online;

                        admin.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&f[&c系統&f] &7» &f成功阻止由玩家 &d" + player.getName() + " &f發出的指令 &d" + command));

                    }

                }

            }

        }

    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {

        if (!(event.getSender() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getSender();
        String command = event.getBuffer().replace(" ", "");

        for (int i = 0 ; i < targetCommands.size() ; i++){

            if (command.contains(targetCommands.get(i))  && !(player.hasPermission("speserver.plhiderplugin.checkbypass"))) {

                event.setCancelled(true);

            }

        }

    }

}
