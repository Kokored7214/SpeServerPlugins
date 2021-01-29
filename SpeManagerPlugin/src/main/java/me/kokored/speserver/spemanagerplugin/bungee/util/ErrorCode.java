package me.kokored.speserver.spemanagerplugin.bungee.util;

public class ErrorCode {

    static String smp = "smpbc_";

    public static String getErrorCode(String happen) {

        switch (happen) {

            //資料庫尚未設定或未連綫
            case "no_sql_connect":
                return smp + "sql_100";

        }

        //未知錯誤
        return "unknown_error";
    }

}
