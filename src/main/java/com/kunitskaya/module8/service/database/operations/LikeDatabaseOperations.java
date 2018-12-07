package com.kunitskaya.module8.service.database.operations;

import com.kunitskaya.module8.domain.Like;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class LikeDatabaseOperations extends DatabaseOperations {

    public LikeDatabaseOperations() {
        super();
        createTable();
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS likes (postid INT NOT NULL, userid INT NOT NULL, timestamp TIMESTAMP)";
        LOGGER.info("Creating table: " + query);

        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLike(Like like) {
        addLike(like.getPostId(), like.getUserId(), like.getTimestamp());
    }

    public void addLike(int postId, int userId, Timestamp timestamp) {
        String query = sqlQueryBuilder.insertPrepared(LIKES_TABLE, String.valueOf(postId), String.valueOf(userId), String.valueOf(timestamp))
                                      .toString();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFrom() {
        deleteFrom(LIKES_TABLE);
    }

    public void printCount() {
        printCount(LIKES_TABLE);
    }

}