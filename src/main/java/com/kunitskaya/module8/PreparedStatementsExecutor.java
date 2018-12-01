package com.kunitskaya.module8;

import com.kunitskaya.logging.ProjectLogger;

import java.sql.*;

public class PreparedStatementsExecutor {

    public void executePreparedStatement(String db, String statement, String param, String... columns) {
        statement = String.format(statement, columns);

        try (Connection connection = MyFirstConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(statement)) {

            Statement st = connection.createStatement();
            String useDbStatement = "USE " + db;
            st.execute(useDbStatement);

            prepStatement.setString(1, param);

            ResultSet resultSet = prepStatement.executeQuery();

            while (resultSet.next()) {
                String column1 = resultSet.getString(1);
                ProjectLogger.LOGGER.info("Prepared statement 1st column result: " + column1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
