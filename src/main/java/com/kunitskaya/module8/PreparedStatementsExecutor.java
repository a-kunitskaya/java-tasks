package com.kunitskaya.module8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementsExecutor {
    private static String selectSimple = "SELECT ? FROM ? WHERE ? = ?";

    public void executePreparedStatement(String statement, String ... params){
    try(Connection connection = DatabaseConnectionProvider.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(statement)) {

        for (int i = 1; i < params.length; i++) {
            preparedStatement.setString(i, params[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

}
