package com.kunitskaya.module8.service.database.operations;

import com.kunitskaya.module8.domain.Friendship;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class FriendshipDatabaseOperations extends DatabaseOperations {

    public FriendshipDatabaseOperations() {
        super();
        createTable();
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS friendships (userid1 INT NOT NULL, userid2 INT NOT NULL, timestamp TIMESTAMP, PRIMARY KEY (userid1))";
        LOGGER.info("Creating table: " + query);

        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFriendshipPrepared(int userId1, int userId2, Timestamp timestamp){
        String query;
    }

    public void addFriendship(Friendship friendship) {

    }

    //    public void addUserWithPreparedStatement(int id, String name, String surname, Date birthDate) {
    //        String query = sqlQueryBuilder.insertPrepared(tableName, String.valueOf(id), name, surname, birthDate.toString())
    //                                      .toString();
    //
    //        try {
    //            PreparedStatement preparedStatement = connection.prepareStatement(query);
    //            preparedStatement.setInt(1, id);
    //            preparedStatement.setString(2, name);
    //            preparedStatement.setString(3, surname);
    //            preparedStatement.setObject(4, birthDate);
    //
    //            LOGGER.info("Executing prepared statement: " + preparedStatement.toString());
    //            preparedStatement.executeUpdate();
    //
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        }
    //
    //    }
}
