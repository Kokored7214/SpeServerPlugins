package me.kokored.speserver.sperulesplugin.sql;

import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;

public class MySqlAPI {

    static MySQL mySQL = SpeRulesPlugin.getSQL();

    public static boolean playRulesConfirmed(String uuid) {
        Boolean b = mySQL.playRulesConfirmed(uuid);
        return b;
    }

    public static void setLinkData(String uuid, String name, String date, Boolean stats) {
        mySQL.setLinkData(uuid, name, date, stats);
    }

    public static void unsetLinkData(String uuid) {
        mySQL.unsetLinkData(uuid);
    }

}
