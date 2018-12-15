package com.kunitskaya.module8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ConnectionProvider {

    private static ConfigProvider configProvider = ConfigProvider.getInstance();

    private static String databaseUrl = configProvider.getDBUrl();
    private static String username = configProvider.getDBUsername();
    private static String password = configProvider.getDBPassword();

    private static Connection connection;

    private ConnectionProvider() {
    }

    public static Connection getInstance() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    private static void connect() {
        String message = "Getting connection to databaseUrl: %s with username: %s, password: %s";
        LOGGER.info(String.format(message, databaseUrl, username, password));
        {
            try {
                connection = DriverManager.getConnection(databaseUrl, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(){
        try {
            LOGGER.info("Closing connection...");
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
