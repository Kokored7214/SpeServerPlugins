package me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.PlayRules;

import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesItem.PlayRules;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class GUIBuilder {

    static Plugin plugin = SpeRulesPlugin.getPlugin(SpeRulesPlugin.class);

    public static Inventory getPlayRulesGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player,
                54, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RulesGUI.GUITitle")));

        inventory.setItem(0, PlayRules.A());
        inventory.setItem(2, PlayRules.A1());

        return inventory;
    }

}
