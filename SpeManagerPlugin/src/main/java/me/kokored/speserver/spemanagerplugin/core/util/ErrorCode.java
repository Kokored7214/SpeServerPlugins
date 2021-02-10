package me.kokored.speserver.spemanagerplugin.core.util;

public class ErrorCode {

    static String smp = "smpsc_";

    //MySQL類
    public static String sql_no_sql_connect() {
        return "sql_101";
    }

    //經濟類
    public static String vault_eco_null() {
        return "eco_null";
    }

    //Minecraft賬號類
    public static String account_mcleaks() {
        return "acc_101";
    }

}
