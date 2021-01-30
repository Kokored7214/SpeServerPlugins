package me.kokored.speserver.spemanagerplugin.bungee.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MinecraftAPI {

    public static String getPlayerAvatars(String uuid) {
        String return_text = "https://crafatar.com/avatars/" + uuid.replace("-", "") + ".png?overlay";
        return return_text;
    }

    public static String getPlayerAvatars(ProxiedPlayer player) {
        String return_text = "https://crafatar.com/avatars/" + player.getUniqueId().toString().replace("-", "") + ".png?overlay";
        return return_text;
    }

    public static Boolean testMcLeaks(ProxiedPlayer player) throws Exception {
        String uuid = player.getUniqueId().toString();

        URL urlObj = new URL("https://mcleaks.themrgong.xyz/api/v3/isuuidmcleaks/" + uuid);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String result = response.toString();
        JsonElement jsonElement = new JsonParser().parse(result).getAsJsonObject().get("isMcleaks");
        if (jsonElement.getAsBoolean()) {
            return true;
        } else {
            return false;
        }
    }

}
