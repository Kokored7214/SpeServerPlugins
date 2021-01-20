package me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI;

import me.kokored.speserver.sperulesplugin.rulesManager.rulesItem.PlayRules;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayRulesGUIBuilder {

    public static Inventory getPlayRulesGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player,
                54, ChatColor.translateAlternateColorCodes('&', "&9&l無語伺服器 &6A章 &f- &6游玩規章"));

        inventory.setItem(0, PlayRules.A());
        inventory.setItem(9, PlayRules.NeedToKnown());
        inventory.setItem(16, PlayRules.Agree());

        inventory.setItem(2, PlayRules.A1());
        inventory.setItem(3, PlayRules.A2());
        inventory.setItem(4, PlayRules.A3());

        inventory.setItem(5, PlayRules.A4());
        inventory.setItem(6, PlayRules.A5());
        inventory.setItem(7, PlayRules.A6());

        inventory.setItem(8, PlayRules.A7());

        //Next line
        inventory.setItem(11, PlayRules.A8());
        inventory.setItem(12, PlayRules.A9());

        inventory.setItem(13, PlayRules.A10());
        inventory.setItem(14, PlayRules.A11());

        return inventory;
    }

    public static Inventory getPlayRulesConfirmGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player,
                27, ChatColor.translateAlternateColorCodes('&', "&b最終確認"));

        inventory.setItem(11, PlayRules.ConfirmAgree());
        inventory.setItem(13, PlayRules.ConfirmInfo());
        inventory.setItem(15, PlayRules.ConfirmDeny());

        return inventory;
    }

    public static Inventory getReadPlayRulesGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player,
                54, ChatColor.translateAlternateColorCodes('&', "&6A章 &f- &6游玩規章"));

        inventory.setItem(0, PlayRules.A());
        inventory.setItem(9, PlayRules.NeedToKnown());

        inventory.setItem(2, PlayRules.A1());
        inventory.setItem(3, PlayRules.A2());
        inventory.setItem(4, PlayRules.A3());

        inventory.setItem(5, PlayRules.A4());
        inventory.setItem(6, PlayRules.A5());
        inventory.setItem(7, PlayRules.A6());

        inventory.setItem(8, PlayRules.A7());

        //Next line
        inventory.setItem(11, PlayRules.A8());
        inventory.setItem(12, PlayRules.A9());
        inventory.setItem(13, PlayRules.A10());

        inventory.setItem(14, PlayRules.A11());

        return inventory;
    }

}
