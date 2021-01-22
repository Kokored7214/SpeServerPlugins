package me.kokored.speserver.sperulesplugin.command;

import java.util.ArrayList;
import java.util.List;
import me.kokored.speserver.sperulesplugin.command.subCommands.Check;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor, TabCompleter {

    private List<SubCommand> subCommand = new ArrayList<>();;

    public MainCommand() {
        Bukkit.getPluginCommand("srp").setExecutor(this);

        subCommand.add(new Check());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player || sender instanceof ConsoleCommandSender) {

            if (args.length >= 1) {

                SubCommand sub = getCommand(args[0]);
                if (sub != null) {

                    if (sender.hasPermission(sub.getPermission())) {

                        sub.onCommand(sender, command, label, args);

                    } else {

                        sender.sendMessage(ChatColor.RED + "你沒有權限使用這個指令");

                    }

                } else {

                    sender.sendMessage(ChatColor.RED + "找不到這個指令");

                }

                return true;
            }

            return false;

        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (!(command.getName().equalsIgnoreCase("srp"))) {
            return null;
        }

        if (args.length == 1) {
            List<String> sub = new ArrayList<>();

            for (SubCommand subCmd : subCommand) {
                if (sender instanceof Player && sender.hasPermission(subCmd.getPermission())) {
                    sub.add(subCmd.getName());
                }
            }

            return sub;
        }

        return null;
    }

    private SubCommand getCommand(String name) {
        for (SubCommand subHandler : subCommand) {
            if (subHandler.getName().equals(name)) {
                return subHandler;
            }
        }
        return null;
    }

}
