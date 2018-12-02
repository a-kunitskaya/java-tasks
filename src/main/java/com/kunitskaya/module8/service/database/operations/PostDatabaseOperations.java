package com.kunitskaya.module8.service.database.operations;

import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class PostDatabaseOperations extends DatabaseOperations {

    public PostDatabaseOperations() {
        super();
        createTable();
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS posts (id INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, text VARCHAR(50), timestamp TIMESTAMP, PRIMARY KEY (id))";
        LOGGER.info("Creating table: " + query);

        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}