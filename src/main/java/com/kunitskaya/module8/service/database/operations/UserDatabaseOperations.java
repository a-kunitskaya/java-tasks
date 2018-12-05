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

    public List<String> getPopularUsers(String periodFrom) {
        List<String> users = new ArrayList<>();

        String subQuery2 = sqlQueryBuilder.select()
                                          .count("userid")
                                          .from("friendships")
                                          .toString();

        System.out.println(subQuery2);

        String query = sqlQueryBuilder.select()
                                      .distinct(TABLE_NAME.concat(".name"))
                                      .from(TABLE_NAME)
                                      .join("likes")
                                      .on(TABLE_NAME, "id", "likes", "userid")
                                      .where("likes.timestamp > " + periodFrom)
                                      .toString();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                users.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> usersWithLikes = new ArrayList<>();

        users.forEach(u -> {
            String q1 = sqlQueryBuilder.select()
                                       .count("userid")
                                       .from("likes")
                                       .where("userid = '" + u + "'")
                                       .toString();

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(q1);
                while (resultSet.next()) {
                    int result = resultSet.getInt(1);
                    if (result > 100) {

                        String q2 = sqlQueryBuilder.select()
                                                   .count("userid1")
                                                   .from("friendships")
                                                   .where("userid = '" + u + "'")
                                                   .toString();

                        try (Statement st = connection.createStatement()) {
                            ResultSet rs = statement.executeQuery(query);
                            while (resultSet.next()) {
                                result = resultSet.getInt(1);
                                if (result > 100) {
                                    usersWithLikes.add(u);
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return users;
    }


    // select distinct users.name
    // from users
    // join friendships
    // on users.id=friendships.userId1
    // join likes on users.id=likes.userid
    // where likes.timestamp > 2025-00-00
    // and likes.userid = (select count(likes.userid) > 100);

    //  SELECT  DISTINCT users.name FROM users  JOIN friendships  ON users.id=friendships.userid1  JOIN likes  ON users.id=likes.userid WHERE likes.timestamp > 2025-00-00  AND likes.userId =(SELECT  COUNT(userid) FROM likes WHERE userId > 100 ) AND friendships.userid1 =(SELECT  COUNT(userid) FROM friendships WHERE userid1 > 100 )

    //select distinct users.name from users join friendships on users.id=friendships.userId1 join likes on users.id=likes.userid where likes.timestamp > 2025-00-00;


    //1. select distinct users.name from users join friendships on users.id=friendships.userId1;
    //2. for each name ->
    //select count(users.name) from users join likes on users.id=likes.userid where likes.timestamp > 2025-00-00 AND users.name = 'name';
    //3.return what returns > 100
}
