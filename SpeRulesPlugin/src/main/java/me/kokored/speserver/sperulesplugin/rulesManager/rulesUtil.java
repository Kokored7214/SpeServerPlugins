package me.kokored.speserver.sperulesplugin.rulesManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class rulesUtil {

    public static String getData() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(now);
    }

}
