package com.kunitskaya.module8;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class MyFirstConnection {

    public void  executeStatements(List<String> voidQueries, List<String> queriesWithResult) {

        try (Connection connection = DatabaseConnectionProvider.getConnection()) {
            if (connection != null) {
                Statement statement = connection.createStatement();

                executeVoidStatements(statement, voidQueries);
                executeStatementsWithResults(statement, queriesWithResult);
            }

        } catch (SQLException e) {
            LOGGER.error("Could no create statement");
            e.printStackTrace();
        }
    }

    private void executeVoidStatements(Statement statement, List<String> voidQueries) {
        voidQueries.forEach(q -> {
            try {
                LOGGER.info("Executing query: " + q);
                statement.execute(q);
            } catch (SQLException e) {
                LOGGER.error("Could not execute void query: " + q);
                e.printStackTrace();
            }
        });
    }

    private void executeStatementsWithResults(Statement statement, List<String> queriesWithResult) {
        List<ResultSet> resultSets = new ArrayList<>();
        queriesWithResult.forEach(q -> {

            try {
                LOGGER.info("Executing query: " + q);
                ResultSet resultSet = statement.executeQuery(q);
                resultSets.add(resultSet);
                printIntResult(resultSet);
            } catch (SQLException e) {
                LOGGER.error("Could not execute query with result: " + q);
                e.printStackTrace();
            }
        });
    }

    private void printIntResult(ResultSet result){
        try {
            while (result.next()) {

                LOGGER.info("int result: " + result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
