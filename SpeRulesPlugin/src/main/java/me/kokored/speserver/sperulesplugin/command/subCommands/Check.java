package me.kokored.speserver.sperulesplugin.command.subCommands;

import me.kokored.speserver.sperulesplugin.command.SubCommand;
import me.kokored.speserver.sperulesplugin.sql.MySqlAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Check extends SubCommand {

    public Check() {
        super("check", "speserver.rulesplugin.command.check");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(args.length == 2)) {
            sender.sendMessage(ChatColor.RED + "參數格式錯誤 " + ChatColor.AQUA + "/srp check [player]");
            return false;
        }

        if (MySqlAPI.getDbStats() == false) {
            sender.sendMessage(ChatColor.YELLOW + "資料庫尚未就緒, 無法只用此指令");
            return false;
        }

        String name = args[1];

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "" +
                "&f---------------------------------------" + "\n" +
                "&b條約資料查詢系統" + "\n" +
                " \n" +
                "&d以下是玩家 &b" + name + " &d的查詢結果" + "\n" +
                " \n" +
                "&bA章(游玩規章): " + getPlayRulesStatus(name) + "\n" +
                "&bB章(聊天規章): " + getChatRulesStatus(name) + "\n" +
                "&f---------------------------------------"));

        return false;
    }

    private String getPlayRulesStatus(String name) {
        Boolean status = MySqlAPI.playRulesConfirmedByName(name);
        if (status == true) {
            return ChatColor.GREEN + "是";
        }
        return ChatColor.RED + "否";
    }

    private String getChatRulesStatus(String name) {
        Boolean status = MySqlAPI.chatRulesConfirmedByName(name);
        if (status == true) {
            return ChatColor.GREEN + "是";
        }
        return ChatColor.RED + "否";
    }

}
