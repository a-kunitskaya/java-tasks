package com.kunitskaya.module8;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class StatementExecutor {
    private static Connection connection = DatabaseConnectionProvider.getConnection();

    public static void executeStatement(String query) {
        try {
            Statement statement = connection.createStatement();
            LOGGER.info("Executing query: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
