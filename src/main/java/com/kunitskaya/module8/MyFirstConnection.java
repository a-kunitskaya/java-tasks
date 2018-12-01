package com.kunitskaya.module8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class MyFirstConnection {
    private static ConfigProvider configProvider = new ConfigProvider();

    private static String databaseUrl = configProvider.getDBUrl();
    private static String username = configProvider.getDBUsername();
    private static String password = configProvider.getDBPassword();
    private static String databaseName = configProvider.getDBName();

    private static MyFirstConnection instance;
    private static Connection connection;

    private MyFirstConnection() {
        getConnection();
    }

    public static Connection getInstance(){
        if(connection == null){

        }
    }


    private Connection getConnection() {
        String message = "Getting connection to databaseUrl: %s with username: %s, password: %s";
        LOGGER.info(String.format(message, databaseUrl, username, password));
        {
            try {
                this.connection = DriverManager.getConnection(databaseUrl, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
