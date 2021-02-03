package me.kokored.speserver.spemanagerplugin.bukkit.features.gui.guis.switchGUI;

import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.features.gui.guis.switchGUI.util.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class SwitchGUI implements CommandExecutor, Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    private static Page1 page1;

    public SwitchGUI() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getPluginCommand("switch").setExecutor(this);

        page1 = new Page1();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!(getPage(1) == null)) {
            player.openInventory(getPage(1));
        }

        return true;
    }

    public static Inventory getPage(Integer page) {

        switch (page){

            case 1:
                return page1.getPage1();

        }

        return null;
    }

}
