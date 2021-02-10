package me.kokored.speserver.spemanagerplugin.bungee.api.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import me.kokored.speserver.spemanagerplugin.bungee.SpeManagerPlugin;
import me.kokored.speserver.spemanagerplugin.bungee.util.Message;

public class CreateTable {

    private static Connection connection;

    static String database = SpeManagerPlugin.getConfig_bungee().getString("SQL.database");

    public void create(Connection connection) {

        this.connection = connection;

        Message.consoleLog("sql", "Loading data table...");

        try {
            spe_system_playerlist();

            Message.consoleLog("sql", "Data table loaded.");
        } catch (SQLException e) {
            Message.consoleLog("sql_warning", "Fail to load Data table!");
            e.printStackTrace();
        }

    }

    private static void spe_system_playerlist() throws SQLException {
        Statement table = connection.createStatement();
        String table_code = "CREATE TABLE IF NOT EXISTS " + database + "." + "spe_system_playerlist"
                + " (" + "uuid CHAR(36) PRIMARY KEY," + "name CHAR(16) NOT NULL,"
                + "join_date CHAR(15)," + "last_update CHAR(40)" + ");";
        table.execute(table_code);
    }

}
