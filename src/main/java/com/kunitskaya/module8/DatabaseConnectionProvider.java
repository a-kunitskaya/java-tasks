package com.kunitskaya.module8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class DatabaseConnectionProvider {
    private static ConfigProvider configProvider = new ConfigProvider();

    private static String db = configProvider.getDBUrl();
    private static String username = configProvider.getDBUsername();
    private static String password = configProvider.getDBPassword();

    public static Connection getConnection() {
        Connection connection = null;

        String message = "Getting connection to database: %s with username: %s, password: &s";
        LOGGER.info(String.format(message, db, username, password));
        try {
            connection = DriverManager.getConnection(db, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
