package me.kokored.speserver.sperulesplugin.sql;

import java.sql.*;
import me.kokored.speserver.sperulesplugin.SpeRulesPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

public class MySQL {

    Plugin plugin = SpeRulesPlugin.getPlugin(SpeRulesPlugin.class);

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
            Statement playrule = connection.createStatement();
            String playrulet = "CREATE TABLE IF NOT EXISTS " + database + "." + "spe_ruleplugin_play"
                    + " (" + "uuid CHAR(36) PRIMARY KEY," + "name CHAR(16) NOT NULL,"
                    + "data CHAR(14)," + "playrules BOOLEAN" + ");";
            playrule.execute(playrulet);

            Statement chatrule = connection.createStatement();
            String chatrulet = "CREATE TABLE IF NOT EXISTS " + database + "." + "spe_ruleplugin_chat"
                    + " (" + "uuid CHAR(36) PRIMARY KEY," + "name CHAR(16) NOT NULL,"
                    + "data CHAR(14)," + "chatrules BOOLEAN" + ");";
            chatrule.execute(chatrulet);
            plugin.getLogger().info("[MySQL] Data table loaded.");
        } catch (SQLException e) {
            plugin.getLogger().warning("[MySQL] Fail to load Data table!");
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerPostLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        updateNameData(player);
    }

    public void updateNameData(Player player) {
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + database + "." + "spe_ruleplugin_play" + " WHERE uuid=?");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                resultSet.beforeFirst();
                resultSet.next();
                String nameFormSql = resultSet.getString("name");
                if (!nameFormSql.equals(name)) {
                    plugin.getLogger().info("[MySQL] Player " + nameFormSql + " login with a new name " + name);
                    PreparedStatement psUpdate = connection.prepareStatement("UPDATE "
                            + database + "." + "spe_ruleplugin_play" + " set name=? where uuid=?;");
                    psUpdate.setString(1, name);
                    psUpdate.setString(2, uuid);
                    psUpdate.executeUpdate();

                    plugin.getLogger().info("[MySQL] Player name updated : " + nameFormSql + " -> " + name);

                }
            }else {

                PreparedStatement psNew = connection.prepareStatement("INSERT INTO " + database + "." +
                        "spe_ruleplugin_play" + " (uuid, name, data, playrules) VALUES (?, ?, ?, ?);");
                psNew.setString(1, uuid);
                psNew.setString(2, name);
                psNew.setString(3, null);
                psNew.setBoolean(4, false);
                psNew.executeUpdate();

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + database + "." + "spe_ruleplugin_chat" + " WHERE uuid=?");
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                resultSet.beforeFirst();
                resultSet.next();
                String nameFormSql = resultSet.getString("name");
                if (!nameFormSql.equals(name)) {
                    PreparedStatement psUpdate = connection.prepareStatement("UPDATE "
                            + database + "." + "spe_ruleplugin_chat" + " set name=? where uuid=?;");
                    psUpdate.setString(1, name);
                    psUpdate.setString(2, uuid);
                    psUpdate.executeUpdate();

                }
            }else {

                PreparedStatement psNew = connection.prepareStatement("INSERT INTO " + database + "." +
                        "spe_ruleplugin_chat" + " (uuid, name, data, playrules) VALUES (?, ?, ?, ?);");
                psNew.setString(1, uuid);
                psNew.setString(2, name);
                psNew.setString(3, null);
                psNew.setBoolean(4, false);
                psNew.executeUpdate();

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playRulesConfirmedByUUID(String uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + database + "." + "spe_ruleplugin_play" + " WHERE uuid=?;");
            ps.setString(1, uuid);
            ResultSet result = ps.executeQuery();
            result.next();
            Boolean b = result.getBoolean("playrules");
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean playRulesConfirmedByName(String name) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + database + "." + "spe_ruleplugin_play" + " WHERE name=?;");
            ps.setString(1, name);
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

    public void setPlayRulesData(String uuid, String name, String data, Boolean stats) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " + database + "." +
                    "spe_ruleplugin_play" + " (uuid, name, data, playrules) VALUES (?, ?, ?, ?);");
            ps.setString(1, uuid);
            ps.setString(2, name);
            ps.setString(3, data);
            ps.setBoolean(4, stats);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unsetPlayRulesDataByUUID(String uuid) {
        if (playRulesConfirmedByUUID(uuid)) {
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM " + database + "." +
                        "spe_ruleplugin_play" + "WHERE uuid=?;");
                ps.setString(1, uuid);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void unsetPlayRulesDataByName(String name) {
        if (playRulesConfirmedByName(name)) {
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM " + database + "." +
                        "spe_ruleplugin_play" + "WHERE name=?;");
                ps.setString(1, name);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean chatRulesConfirmedByUUID(String uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + database + "." + "spe_ruleplugin_chat" + " WHERE uuid=?;");
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

    public boolean chatRulesConfirmedByName(String name) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT COUNT(*) FROM " + database + "." + "spe_ruleplugin_chat" + " WHERE name=?;");
            ps.setString(1, name);
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

    public void setChatRulesData(String uuid, String name, String data, Boolean stats) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " + database + "." +
                    "spe_ruleplugin_chat" + " (uuid, name, data, chatrules) VALUES (?, ?, ?, ?);");
            ps.setString(1, uuid);
            ps.setString(2, name);
            ps.setString(3, data);
            ps.setBoolean(4, stats);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unsetChatRulesDataByUUID(String uuid) {
        if (chatRulesConfirmedByUUID(uuid)) {
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM " + database + "." +
                        "spe_ruleplugin_chat" + "WHERE uuid=?;");
                ps.setString(1, uuid);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void unsetChatRulesDataByName(String name) {
        if (chatRulesConfirmedByName(name)) {
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM " + database + "." +
                        "spe_ruleplugin_chat" + "WHERE name=?;");
                ps.setString(1, name);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
