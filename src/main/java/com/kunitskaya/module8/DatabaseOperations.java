package com.kunitskaya.module8;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class DatabaseOperations {
    @Autowired
    private ConfigProvider configProvider;

    protected Connection connection = MyFirstConnection.getInstance();
    private String projectDatabase = configProvider.getDBName();

    protected void createProjectDatabase() {
        LOGGER.info("Creating database: " + projectDatabase);

        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + projectDatabase;
        try {
            Statement statement = connection.createStatement();
            statement.execute(createDatabaseQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void useProjectDatabase() {
        LOGGER.info("Switching to database: " + projectDatabase);

        String useDatabaseQuery = "USE " + projectDatabase;
        try {
            Statement statement = connection.createStatement();
            statement.execute(useDatabaseQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
