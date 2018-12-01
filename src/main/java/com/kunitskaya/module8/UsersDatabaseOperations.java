package com.kunitskaya.module8;

import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class UsersDatabaseOperations extends DatabaseOperations {

    public UsersDatabaseOperations() {
        createProjectDatabase();
        useProjectDatabase();
        createTable();
    }

    private void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(20), surname  VARCHAR(30), birthdate DATE, PRIMARY KEY (id))";

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDefaultUser(){
        LOGGER.info("Inserting default user");
        String insertUserQuery = "INSERT INTO users VALUES(0, 'Jack', 'White', 19820325)";

        try {
            Statement statement = connection.createStatement();
            statement.execute(insertUserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
