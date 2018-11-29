package com.kunitskaya.module8;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class StatementExecutor {
    @Autowired
    private Connection connection;

    public void executeStatement(String query) {
        try {
            connection = DatabaseConnectionProvider.getConnection();

            Statement statement = connection.createStatement();
            LOGGER.info("Executing query: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
