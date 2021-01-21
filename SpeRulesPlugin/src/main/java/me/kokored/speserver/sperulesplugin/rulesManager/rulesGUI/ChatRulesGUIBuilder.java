package me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI;

import me.kokored.speserver.sperulesplugin.rulesManager.rulesItem.ChatRules;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ChatRulesGUIBuilder {

    public static Inventory getChatRulesGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player,
                54, ChatColor.translateAlternateColorCodes('&', "&9&l無語伺服器 &6B章 &f- &6聊天規章"));

        inventory.setItem(0, ChatRules.B());
        inventory.setItem(9, ChatRules.Agree());

        inventory.setItem(2, ChatRules.B1());
        inventory.setItem(3, ChatRules.B2());
        inventory.setItem(4, ChatRules.B3());

        inventory.setItem(5, ChatRules.B4());
        inventory.setItem(6, ChatRules.B5());
        inventory.setItem(7, ChatRules.B6());

        return inventory;
    }

    public static Inventory getChatRulesConfirmGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player,
                27, ChatColor.translateAlternateColorCodes('&', "&6B章 &f- &6聊天規章 &b最終確認"));

        inventory.setItem(11, ChatRules.ConfirmAgree());
        inventory.setItem(13, ChatRules.ConfirmInfo());
        inventory.setItem(15, ChatRules.ConfirmDeny());

        return inventory;
    }

    public static Inventory getReadChatRulesGUI(Player player) {

        Inventory inventory = Bukkit.createInventory(player,
                54, ChatColor.translateAlternateColorCodes('&', "&6B章 &f- &6聊天規章"));

        inventory.setItem(0, ChatRules.B());

        inventory.setItem(2, ChatRules.B1());
        inventory.setItem(3, ChatRules.B2());
        inventory.setItem(4, ChatRules.B3());

        inventory.setItem(5, ChatRules.B4());
        inventory.setItem(6, ChatRules.B5());
        inventory.setItem(7, ChatRules.B6());

        return inventory;
    }

}
