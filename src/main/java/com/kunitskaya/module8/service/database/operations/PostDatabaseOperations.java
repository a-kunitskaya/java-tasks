package com.kunitskaya.module8.service.database.operations;

import com.kunitskaya.module8.domain.Post;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class PostDatabaseOperations extends DatabaseOperations {
    private static final String TABLE_NAME = "posts";

    public PostDatabaseOperations() {
        super();
        createTable();
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS posts (id INT NOT NULL AUTO_INCREMENT, userid INT NOT NULL, text VARCHAR(50), timestamp TIMESTAMP, PRIMARY KEY (id))";
        LOGGER.info("Creating table: " + query);

        try (Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllPosts() {
        deleteFrom(TABLE_NAME);
    }

    public void addPost(Post post) {
        addPost(post.getId(), post.getUserId(), post.getText(), post.getTimestamp());
    }

    public void addPost(int id, int userId, String text, Timestamp timestamp) {
        String query = sqlQueryBuilder.insertPrepared(TABLE_NAME, String.valueOf(id), String.valueOf(userId), text, String.valueOf(timestamp))
                                      .toString();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, text);
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printPostsCount() {
        printCount(TABLE_NAME);
    }
}