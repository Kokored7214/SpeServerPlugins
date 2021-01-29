package me.kokored.speserver.spemanagerplugin.bungee.sql;

import java.sql.*;
import javax.sql.ConnectionEvent;
import me.kokored.speserver.spemanagerplugin.bungee.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bungee.util.Date;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class MySQL {

    static ProxyServer plugin = SpeManagerPlugin.getProxyServer();

    static Configuration config_bungee = SpeManagerPlugin.getConfig_bungee();

    static Connection connection;

    static Boolean dbstats = false;

    static String sqlType;
    static String host, database, username, password;
    static int port;

    public MySQL() {

        sqlType = config_bungee.getString("SQL.Type");

        host = config_bungee.getString("SQL.host");
        port = config_bungee.getInt("SQL.port");
        database = config_bungee.getString("SQL.database");
        username = config_bungee.getString("SQL.username");
        password = config_bungee.getString("SQL.password");

        if (sqlType.equals("MySQL") || sqlType.equals("MariaDB")) {

            if (database.equals("") || username.equals("") || password.equals("")) {
                plugin.getLogger().warning("[MySQL] Database didn't setup, setup at config.yml file.");
                return;
            }

            try {
                plugin.getLogger().info("[MySQL] Trying to connect MySQL...");
                openConnection();
                createTable();
            } catch (ClassNotFoundException | SQLException e) {
                plugin.getLogger().warning("[MySQL] Fail to connect MySQL!");
                e.printStackTrace();
            }

            return;
        }
        plugin.getLogger().warning("[MySQL] MySQL type was not support...");

    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        if (sqlType.equals("MySQL")) {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database
                            + "?useSSL=false&autoReconnect=true",
                    this.username, this.password);

        }
        if (sqlType.equals("MariaDB")) {

            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mariadb://" + this.host + ":" + this.port + "/" + this.database
                            + "?useSSL=false&autoReconnect=true",
                    this.username, this.password);

        }
        dbstats = true;
        plugin.getLogger().info("[MySQL] Successfully connected to MySQL.");
    }

    public void disconnect() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
            dbstats = false;
            plugin.getLogger().info("[MySQL] Successfully disconnected to MySQL.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean getDbStats() {
        return dbstats;
    }

    public void createTable() {
        try {
            plugin.getLogger().info("[MySQL] Loading data table...");
            Statement plist = connection.createStatement();
            String plist_code = "CREATE TABLE IF NOT EXISTS " + database + "." + "spe_system_playerlist"
                    + " (" + "uuid CHAR(36) PRIMARY KEY," + "name CHAR(16) NOT NULL,"
                    + "join_date CHAR(15)," + "last_update CHAR(40)" + ");";
            plist.execute(plist_code);

            plugin.getLogger().info("[MySQL] Data table loaded.");
        } catch (SQLException e) {
            plugin.getLogger().warning("[MySQL] Fail to load Data table!");
            e.printStackTrace();
        }
    }

    public static Boolean hasPlayer(String uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + database + "." + "spe_system_playerlist" + " WHERE uuid=?;");
            ps.setString(1, uuid);
            ResultSet result = ps.executeQuery();
            result.next();
            Integer resultInt = result.getInt("COUNT(*)");
            if (resultInt == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addPlayer(String uuid, String name, String date, String last_update) {
        if (!(hasPlayer(uuid))) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO " + database + "." +
                        "spe_system_playerlist" + " (uuid, name, join_date, last_update) VALUES (?, ?, ?, ?);");
                ps.setString(1, uuid);
                ps.setString(2, name);
                ps.setString(3, date);
                ps.setString(4, last_update);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removePlayer(String uuid) {
        if (hasPlayer(uuid)) {
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM " + database + "." +
                        "spe_system_playerlist" + "WHERE uuid=?;");
                ps.setString(1, uuid);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updatePlayerName(ProxiedPlayer player) {
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + database + "." + "spe_system_playerlist" + " WHERE uuid=?");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                resultSet.beforeFirst();
                resultSet.next();
                String nameFormSql = resultSet.getString("name");
                if (!nameFormSql.equals(name)) {
                    plugin.getLogger().info("[MySQL] Player " + nameFormSql + " login with a new name " + name);
                    PreparedStatement psUpdate = connection.prepareStatement("UPDATE "
                            + database + "." + "spe_system_playerlist" + " set name=?, last_update=? where uuid=?;");
                    psUpdate.setString(1, name);
                    psUpdate.setString(2, "update_name," + Date.getDate());
                    psUpdate.setString(3, uuid);
                    psUpdate.executeUpdate();

                    plugin.getLogger().info("[MySQL] Player name updated : " + nameFormSql + " -> " + name);

                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
