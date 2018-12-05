package com.kunitskaya.module8.service.database.operations;


import com.kunitskaya.module8.domain.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class UserDatabaseOperations extends DatabaseOperations {
    private static final String TABLE_NAME = "users";

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

        String query = sqlQueryBuilder.insert(TABLE_NAME, String.valueOf(id), name, surname, date).toString();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserWithPreparedStatement(int id, String name, String surname, Date birthDate) {
        String query = sqlQueryBuilder.insertPrepared(TABLE_NAME, String.valueOf(id), name, surname, birthDate.toString())
                                      .toString();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setObject(4, birthDate);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printUsersCount() {
        printCount(TABLE_NAME);
    }

    public void deleteAllUsers() {
        deleteFrom(TABLE_NAME);
    }

    public void addUser(User user) {
        addUserWithPreparedStatement(user.getId(), user.getName(), user.getSurname(), user.getBirthDate());
    }

    public List<String> getPopularUsers(int numberOfFriends, int numberOfLikes, Timestamp periodFrom) {
        String query = sqlQueryBuilder.select()
                                      .distinct(TABLE_NAME.concat(".name"))
                                      .from(TABLE_NAME)
                                      .join("friendships")
                                      .on(TABLE_NAME, "id", "friendships", "userId")
                                      .toString();
    }


    //1. select distinct users.name from users join friendships on users.id=friendships.userId1;
    //2. for each name ->
    //select count(users.name) from users join likes on users.id=likes.userid where likes.timestamp > 2025-00-00 AND users.name = 'name';
    //3.return what returns > 100
}
