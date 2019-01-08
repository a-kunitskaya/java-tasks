package com.kunitskaya.module8.service.database.operations;


import com.kunitskaya.module8.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
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

    public List<String> getPopularUsers(ZonedDateTime date, int likesCount, int friendshipsCount) {
        List<String> users = new ArrayList<>();
        long timestamp = date.toEpochSecond();

        String query = "select distinct(users.name) from users " +
                "join(select userid, count(userid) as lc from likes where likes.timestamp > ? group by likes.userid) " +
                "as l on users.id = l.userid " +
                "join (select userid1, count(userid1) as fc from friendships where timestamp > ? group by  friendships.userid1) " +
                "as f on users.id = f.userid1 " +
                "where lc > ? " +
                "AND fc > ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, timestamp);
            preparedStatement.setLong(2, timestamp);
            preparedStatement.setInt(3, likesCount);
            preparedStatement.setInt(4, friendshipsCount);
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
