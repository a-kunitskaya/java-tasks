package com.kunitskaya.module8.service.database.operations;

import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class FriendshipDatabaseOperations extends DatabaseOperations {

    public FriendshipDatabaseOperations() {
        super();
        createTable();
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS friendships (userid1 INT NOT NULL, userid2 INT NOT NULL, timestamp TIMESTAMP, PRIMARY KEY (userid1))";
        LOGGER.info("Creating table: " + query);

        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
