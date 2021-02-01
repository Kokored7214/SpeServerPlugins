package me.kokored.speserver.spemanagerplugin.core.util;

import java.text.SimpleDateFormat;

public class Date {

    //取得現在日期及時間
    public static String getDate() {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(now);
    }

    //取得現在日期及時間(自定格式)
    public static String getDate(String format) {
        java.util.Date now = new java.util.Date();
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return format1.format(now);
    }

}
