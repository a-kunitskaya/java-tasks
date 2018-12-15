package com.kunitskaya.module8.service.database.operations;

import com.kunitskaya.module8.ConfigProvider;
import com.kunitskaya.module8.ConnectionProvider;
import com.kunitskaya.module8.service.database.SqlQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class DatabaseOperations {
    @Autowired
    protected SqlQueryBuilder sqlQueryBuilder;

    private static ConfigProvider configProvider = ConfigProvider.getInstance();

    protected static final String USERS_TABLE = "users";
    protected static final String FRIENDSHIPS_TABLE = "friendships";
    protected static final String LIKES_TABLE = "likes";
    protected static final String POSTS_TABLE = "posts";
    protected static final String DATABASE = configProvider.getDBName();

    protected Connection connection = ConnectionProvider.getInstance();

    public DatabaseOperations() {
        createProjectDatabase();
        useProjectDatabase();
    }

    private void createProjectDatabase() {
        LOGGER.info("Creating database: " + DATABASE);

        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + DATABASE;
        try (Statement statement = connection.createStatement()) {

            statement.execute(createDatabaseQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void useProjectDatabase() {
        LOGGER.info("Switching to database: " + DATABASE);

        String useDatabaseQuery = "USE " + DATABASE;
        try (Statement statement = connection.createStatement()) {

            statement.execute(useDatabaseQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void deleteFrom(String tableName) {
        String query = sqlQueryBuilder.delete(tableName)
                                      .toString();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllTables() {
        String query = "SHOW TABLES";

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            LOGGER.info("Printing all tables:");
            while (result.next()) {
                LOGGER.info(result.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void printCount(String tableName) {
        String query = sqlQueryBuilder.select()
                                      .count("*")
                                      .from(tableName)
                                      .toString();

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                LOGGER.info(tableName + " count in database: " + result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDatabase() {
        LOGGER.info("Deleting database: " + DATABASE);
        String query = "DROP DATABASE " + DATABASE;

        try (Statement statement = connection.createStatement()) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
