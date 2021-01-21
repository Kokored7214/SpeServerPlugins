package me.kokored.speserver.sperulesplugin;

import me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.ChatRulesGUI;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesGUI.PlayRulesGUI;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesItem.ChatRules;
import me.kokored.speserver.sperulesplugin.rulesManager.rulesItem.PlayRules;
import me.kokored.speserver.sperulesplugin.sql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeRulesPlugin extends JavaPlugin {

    static MySQL mySQL;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        mySQL = new MySQL();

        new PlayRulesGUI();
        new PlayRules();

        new ChatRulesGUI();
        new ChatRules();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        mySQL.disconnect();

    }

    public static MySQL getSQL() {
        return mySQL;
    }

}
