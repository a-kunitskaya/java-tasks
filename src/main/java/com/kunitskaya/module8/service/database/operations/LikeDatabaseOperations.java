package com.kunitskaya.module8.service.database.operations;

import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class LikeDatabaseOperations extends DatabaseOperations {

    public LikeDatabaseOperations() {
        super();
        createTable();
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS likes (postid INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, timestamp TIMESTAMP, PRIMARY KEY (postid))";
        LOGGER.info("Creating table: " + query);

        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
