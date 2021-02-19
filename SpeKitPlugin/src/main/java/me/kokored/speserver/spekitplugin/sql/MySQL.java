package me.kokored.speserver.spekitplugin.sql;

import java.sql.*;
import me.kokored.speserver.spekitplugin.SpeKitPlugin;
import org.bukkit.plugin.Plugin;

public class MySQL {

    Plugin plugin = SpeKitPlugin.getPlugin(SpeKitPlugin.class);

    String sqlType;

    Connection connection;

    Boolean dbstats = false;

    String host, database, username, password;
    int port;

    public MySQL() {

        sqlType = plugin.getConfig().getString("SQL.Type");

        host = plugin.getConfig().getString("SQL.host");
        port = plugin.getConfig().getInt("SQL.port");
        database = plugin.getConfig().getString("SQL.database");
        username = plugin.getConfig().getString("SQL.username");
        password = plugin.getConfig().getString("SQL.password");

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

    public Boolean getDbStats() {
        return dbstats;
    }

    public void createTable() {
        try {
            plugin.getLogger().info("[MySQL] Loading data table...");
            Statement kit = connection.createStatement();
            String kitt = "CREATE TABLE IF NOT EXISTS " + database + "." + "spe_kitplugin"
                    + " (" + "uuid CHAR(36) PRIMARY KEY," + "name CHAR(16) NOT NULL,"
                    + "last_kit CHAR(36)," + "timeout INT(14)" + ");";
            kit.execute(kitt);

            plugin.getLogger().info("[MySQL] Data table loaded.");
        } catch (SQLException e) {
            plugin.getLogger().warning("[MySQL] Fail to load Data table!");
            e.printStackTrace();
        }
    }

}
