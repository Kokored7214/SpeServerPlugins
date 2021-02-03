package me.kokored.speserver.spemanagerplugin.bukkit.sql;

import java.sql.*;
import me.kokored.speserver.spemanagerplugin.bukkit.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bukkit.config.Configs;
import me.kokored.speserver.spemanagerplugin.bukkit.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

public class MySQL implements Listener {

    Plugin plugin = SpeManagerPlugin.getPlugin(SpeManagerPlugin.class);

    static Connection connection;

    static Boolean dbstats = false;

    static String sqlType;
    static String host, database, username, password;
    static int port;

    public MySQL() {

        Bukkit.getPluginManager().registerEvents(this, plugin);

        sqlType = Configs.get("config_bukkit").getString("SQL.Type");

        host = Configs.get("config_bukkit").getString("SQL.host");
        port = Configs.get("config_bukkit").getInt("SQL.port");
        database = Configs.get("config_bukkit").getString("SQL.database");
        username = Configs.get("config_bukkit").getString("SQL.username");
        password = Configs.get("config_bukkit").getString("SQL.password");

        if (sqlType.equals("MySQL") || sqlType.equals("MariaDB")) {

            if (database.equals("") || username.equals("") || password.equals("")) {
                Message.consoleLog("sql_warning", "Database didn't setup, setup at config.yml file.");
                return;
            }

            try {
                Message.consoleLog("sql", "Trying to connect MySQL...");
                openConnection();
                createTable();
            } catch (ClassNotFoundException | SQLException e) {
                Message.consoleLog("sql_warning", "Fail to connect MySQL!");
                e.printStackTrace();
            }

            return;
        }
        Message.consoleLog("sql_warning", "MySQL type was not support...");

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
        Message.consoleLog("sql", "Successfully connected to MySQL.");
    }

    public void disconnect() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
            dbstats = false;
            Message.consoleLog("sql", "Successfully disconnected to MySQL.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean getDbStats() {
        return dbstats;
    }

    public void createTable() {
        try {
            Message.consoleLog("sql", "Loading data table...");

            Statement feature_switch = connection.createStatement();
            String feature_switch_code = "CREATE TABLE IF NOT EXISTS " + database + "." + "spe_system_feature_switch"
                    + " (" + "uuid CHAR(36) PRIMARY KEY," + "name CHAR(16) NOT NULL,"
                    + "death_exp INT(1));";
            feature_switch.execute(feature_switch_code);

            Statement feature_notify = connection.createStatement();
            String feature_notify_code = "CREATE TABLE IF NOT EXISTS " + database + "." + "spe_system_feature_notify"
                    + " (" + "uuid CHAR(36) PRIMARY KEY," + "name CHAR(16) NOT NULL,"
                    + "death_exp BOOLEAN);";
            feature_notify.execute(feature_notify_code);

            Message.consoleLog("sql", "Data table loaded.");
        } catch (SQLException e) {
            Message.consoleLog("sql_warning", "Fail to load Data table!");
            e.printStackTrace();
        }
    }

    public static Boolean hasPlayer(String uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + database + "." + "spe_system_feature_switch" + " WHERE uuid=?;");
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

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        setupDefaultData(player.getUniqueId().toString(), player.getName());
    }

    public static void setupDefaultData(String uuid, String name) {
        if (!(hasPlayer(uuid))) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO " + database + "." +
                        "spe_system_feature_switch" + " (uuid, name, death_exp) VALUES (?, ?, ?);");
                ps.setString(1, uuid);
                ps.setString(2, name);
                ps.setInt(3, 0);
                ps.executeUpdate();

                PreparedStatement ps1 = connection.prepareStatement("INSERT INTO " + database + "." +
                        "spe_system_feature_notify" + " (uuid, name, death_exp) VALUES (?, ?, ?);");
                ps1.setString(1, uuid);
                ps1.setString(2, name);
                ps1.setBoolean(3, true);
                ps1.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
