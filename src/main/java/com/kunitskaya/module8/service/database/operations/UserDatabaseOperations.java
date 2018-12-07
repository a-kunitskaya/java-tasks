package com.kunitskaya.module8.service.database.operations;


import com.kunitskaya.module8.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<String> getPopularUsers(String periodFrom, int likesCount, int friendshipsCount) {

        String message = "Found popular user: %s, likes from: %s: %s, friendships: %s";
        Map<String, Integer> likes = getUsersWithLikes(periodFrom, likesCount);
        Map<String, Integer> friendships = getUsersWithFriendships(friendshipsCount);
        List<String> users = new ArrayList<>();

        for (Map.Entry<String, Integer> userLikes : likes.entrySet()) {
            friendships.entrySet()
                       .stream()
                       .filter(f -> f.getValue() >= friendshipsCount)
                       .filter(l -> userLikes.getValue() >= likesCount)
                       .peek(u -> LOGGER.info(String.format(message, u.getKey(), periodFrom, userLikes.getValue(), u.getValue())))
                       .map(Map.Entry::getKey)
                       .forEach(users::add);
        }
        return users;
    }

    private Map<String, Integer> getUsersWithLikes(String periodFrom, int likesCount) {
        Map<String, Integer> likesPerUser = new HashMap<>();

        String query = sqlQueryBuilder.select()
                                      .distinct(USERS_TABLE.concat(".name") + ", count(*)")
                                      .from(USERS_TABLE)
                                      .join(LIKES_TABLE)
                                      .on(USERS_TABLE, "id", LIKES_TABLE, "userid")
                                      .where(LIKES_TABLE.concat(".timestamp > ") + periodFrom)
                                      .groupBy(USERS_TABLE.concat(".name"))
                                      .having("COUNT(*) > " + likesCount)
                                      .toString();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet1 = statement.executeQuery(query);
            while (resultSet1.next()) {
                String user = resultSet1.getString(1);
                int likes = resultSet1.getInt(2);
                if (likes > 100) {
                    likesPerUser.put(user, likes);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likesPerUser;
    }

    private Map<String, Integer> getUsersWithFriendships(int friendshipsCount) {
        Map<String, Integer> friendshipsPerUser = new HashMap<>();

        String query = sqlQueryBuilder.select()
                                      .distinct(USERS_TABLE.concat(".name") + ", count(*)")
                                      .from(USERS_TABLE)
                                      .join(FRIENDSHIPS_TABLE)
                                      .on(USERS_TABLE, "id", FRIENDSHIPS_TABLE, "userid1")
                                      .groupBy(USERS_TABLE.concat(".name"))
                                      .having("COUNT(*) > " + friendshipsCount)
                                      .toString();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet1 = statement.executeQuery(query);
            while (resultSet1.next()) {
                String user = resultSet1.getString(1);
                int friendships = resultSet1.getInt(2);
                if (friendships > 100) {
                    friendshipsPerUser.put(user, friendships);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendshipsPerUser;
    }
}
