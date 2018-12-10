package com.kunitskaya.module8;

import com.kunitskaya.logging.ProjectLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Connection should be closed in methods using this class
 */
public class PreparedStatementsExecutor {

    public void executePreparedStatement(boolean isWithResult, String statement, String param, String... columns) {
        statement = String.format(statement, columns);

        try {
            Connection connection = MyFirstConnection.getInstance();
            PreparedStatement prepStatement = connection.prepareStatement(statement);
            prepStatement.setString(1, param);

            if (isWithResult) {
                ResultSet resultSet = prepStatement.executeQuery();
                while (resultSet.next()) {
                    String column1 = resultSet.getString(1);
                    ProjectLogger.LOGGER.info("Prepared statement 1st column result: " + column1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
