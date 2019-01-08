package com.kunitskaya.module8.service.database.operations;


import com.kunitskaya.module8.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class UserDatabaseOperations extends DatabaseOperations {

    public UserDatabaseOperations() {
        super();
        createTable();
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(20), surname  VARCHAR(30), birthdate DATE, PRIMARY KEY (id))";
        LOGGER.info("Creating table: " + query);

        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(int id, String name, String surname, Date birthDate) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(birthDate);

        String query = sqlQueryBuilder.insert(USERS_TABLE, String.valueOf(id), name, surname, date).toString();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserWithPreparedStatement(int id, String name, String surname, Date birthDate) {
        String query = sqlQueryBuilder.insertPrepared(USERS_TABLE, String.valueOf(id), name, surname, birthDate.toString())
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

    public void printCount() {
        printCount(USERS_TABLE);
    }

    public void deleteFrom() {
        deleteFrom(USERS_TABLE);
    }

    public void addUser(User user) {
        addUserWithPreparedStatement(user.getId(), user.getName(), user.getSurname(), user.getBirthDate());
    }

    public List<String> getPopularUsers(String date, int likesCount, int friendshipsCount) {
        List<String> users = new ArrayList<>();

        String query = "select distinct(users.name) from users " +
                "join(select userid, count(userid) from likes group by  likes.userid) as l on users.id = l.userid " +
                "join (select userid1, count(userid1)from friendships group by  friendships.userid1) as f on users.id = f.userid1 " +
                "where (select count(likes.userid) from likes where likes.timestamp > ?) > ? " +
                "AND (select count(friendships.userid1) from friendships where friendships.timestamp > ?) > ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, likesCount);
            preparedStatement.setObject(2, date);
            preparedStatement.setInt(3, friendshipsCount);
            preparedStatement.setObject(4, date);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userName = resultSet.getString(1);
                users.add(userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
