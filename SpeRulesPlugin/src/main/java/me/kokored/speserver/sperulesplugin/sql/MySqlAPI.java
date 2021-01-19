package me.kokored.speserver.sperulesplugin.sql;

import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;

public class MySqlAPI {

    static MySQL mySQL = SpeRulesPlugin.getSQL();

    public static boolean playRulesConfirmed(String uuid) {
        Boolean b = mySQL.playRulesConfirmed(uuid);
        return b;
    }

    public static void setPlayRulesData(String uuid, String name, String date, Boolean stats) {
        mySQL.setPlayRulesData(uuid, name, date, stats);
    }

    public static void unsetPlayRulesData(String uuid) {
        mySQL.unsetPlayRulesData(uuid);
    }


    public static boolean chatRulesConfirmed(String uuid) {
        Boolean b = mySQL.chatRulesConfirmed(uuid);
        return b;
    }

    public static void setChatRulesData(String uuid, String name, String date, Boolean stats) {
        mySQL.setChatRulesData(uuid, name, date, stats);
    }

    public static void unsetChatRulesData(String uuid) {
        mySQL.unsetChatRulesData(uuid);
    }

}
