package com.kunitskaya.module8.service.database.operations;


import com.kunitskaya.module8.domain.User;
import com.kunitskaya.module8.service.database.operations.DatabaseOperations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class UserDatabaseOperations extends DatabaseOperations {
    private static String tableName = "users";

    public UserDatabaseOperations() {
        super();
        createTable();
    }

    private void createTable() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(20), surname  VARCHAR(30), birthdate DATE, PRIMARY KEY (id))";
        LOGGER.info("Creating table: " + createTableQuery);

        try (Statement statement = connection.createStatement();) {
            statement.execute(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(int id, String name, String surname, Date birthDate) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(birthDate);

        String query = sqlQueryBuilder.insert(tableName, String.valueOf(id), name, surname, date).toString();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserWithPreparedStatement(int id, String name, String surname, Date birthDate){
        String query = "INSERT INTO users VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setObject(4, birthDate);

            LOGGER.info("Executing prepared statement: " + preparedStatement.toString());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getUsersCount() {
        String query = sqlQueryBuilder.select()
                                      .count("*")
                                      .from(tableName)
                                      .toString();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                LOGGER.info("Users count in database: " + result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUsers() {
        String query = sqlQueryBuilder.delete(tableName)
                                      .toString();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        addUserWithPreparedStatement(user.getId(), user.getName(), user.getSurname(), user.getBirthDate());
    }
}
