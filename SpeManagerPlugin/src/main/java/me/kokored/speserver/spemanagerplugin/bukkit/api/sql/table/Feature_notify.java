package me.kokored.speserver.spemanagerplugin.bukkit.api.sql.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.api.sql.MySQL;
import org.bukkit.plugin.Plugin;

public class Feature_notify {

    static Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    static Connection connection = MySQL.getConnection();
    static String database = plugin.getConfig().getString("SQL.database");

    public static Boolean getDeathExp(String uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + database + "." + "spe_system_feature_notify WHERE uuid=?");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Boolean b = resultSet.getBoolean("death_exp");
                return b;
            }else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void updateDeathExp(String uuid, Boolean b) {
        try {
            PreparedStatement psUpdate = connection.prepareStatement(
                    "UPDATE " + database + "." + "spe_system_feature_notify SET death_exp=? WHERE uuid=?;");
            psUpdate.setBoolean(1, b);
            psUpdate.setString(2, uuid);
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
