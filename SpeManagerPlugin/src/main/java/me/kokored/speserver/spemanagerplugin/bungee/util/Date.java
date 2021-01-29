package me.kokored.speserver.spemanagerplugin.bungee.util;

import java.text.SimpleDateFormat;

public class Date {

    //取得現在日期及時間
    public static String getDate() {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(now);
    }

}
