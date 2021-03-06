package com.kunitskaya.module8.service.database.operations;

import com.kunitskaya.module8.domain.Friendship;

import java.sql.PreparedStatement;
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
        String query = "CREATE TABLE IF NOT EXISTS friendships (userid1 INT NOT NULL, userid2 INT NOT NULL, timestamp TIMESTAMP)";
        LOGGER.info("Creating table: " + query);

        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFriendship(int userId1, int userId2, Timestamp timestamp) {
        String query = sqlQueryBuilder.insertPrepared(FRIENDSHIPS_TABLE, String.valueOf(userId1), String.valueOf(userId2), String.valueOf(timestamp))
                                      .toString();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId1);
            preparedStatement.setInt(2, userId2);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFriendship(Friendship friendship) {
        addFriendship(friendship.getUserId1(), friendship.getUserId2(), friendship.getTimestamp());
    }

    public void printCount() {
        printCount(FRIENDSHIPS_TABLE);
    }

    public void deleteFrom() {
        deleteFrom(FRIENDSHIPS_TABLE);
    }
}
