package me.kokored.speserver.sperulesplugin.sql;

import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;

public class MySqlAPI {

    static MySQL mySQL = SpeRulesPlugin.getSQL();

    public static boolean getDbStats() {
        return mySQL.getDbStats();
    }

    public static boolean playRulesConfirmedByUUID(String uuid) {
        Boolean b = mySQL.playRulesConfirmedByUUID(uuid);
        return b;
    }

    public static boolean playRulesConfirmedByName(String name) {
        Boolean b = mySQL.playRulesConfirmedByName(name);
        return b;
    }

    public static void setPlayRulesData(String uuid, String name, String date, Boolean stats) {
        mySQL.setPlayRulesData(uuid, name, date, stats);
    }

    public static void unsetPlayRulesDataByUUID(String uuid) {
        mySQL.unsetPlayRulesDataByUUID(uuid);
    }
    public static void unsetPlayRulesDataByName(String name) {
        mySQL.unsetPlayRulesDataByName(name);
    }


    public static boolean chatRulesConfirmedByUUID(String uuid) {
        Boolean b = mySQL.chatRulesConfirmedByUUID(uuid);
        return b;
    }

    public static boolean chatRulesConfirmedByName(String uuid) {
        Boolean b = mySQL.chatRulesConfirmedByName(uuid);
        return b;
    }

    public static void setChatRulesData(String uuid, String name, String date, Boolean stats) {
        mySQL.setChatRulesData(uuid, name, date, stats);
    }

    public static void unsetChatRulesDataByUUID(String uuid) {
        mySQL.unsetChatRulesDataByUUID(uuid);
    }
    public static void unsetChatRulesDataByName(String name) {
        mySQL.unsetChatRulesDataByName(name);
    }

}
