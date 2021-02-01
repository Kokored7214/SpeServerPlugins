package me.kokored.speserver.spemanagerplugin.bukkit.sql.table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.sql.MySQL;
import org.bukkit.plugin.Plugin;

public class Feature_switch {

    static Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    static Connection connection = MySQL.getConnection();
    static String database = plugin.getConfig().getString("SQL.database");

    public static Integer getDeathExp(String uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + database + "." + "spe_system_feature_notify WHERE uuid=?");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Integer i = resultSet.getInt("death_exp");
                return i;
            }else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void updateDeathExp(String uuid, Integer which) {
        try {
            PreparedStatement psUpdate = connection.prepareStatement(
                    "UPDATE " + database + "." + "spe_system_feature_switch SET death_exp=? WHERE uuid=?;");
            psUpdate.setInt(1, which);
            psUpdate.setString(2, uuid);
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
