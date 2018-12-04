package com.kunitskaya.module8.service.database.operations;

import com.kunitskaya.module8.ConfigProvider;
import com.kunitskaya.module8.MyFirstConnection;
import com.kunitskaya.module8.service.database.SqlQueryBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class DatabaseOperations {
    protected Connection connection = MyFirstConnection.getInstance();
    protected SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder();

    private ConfigProvider configProvider = ConfigProvider.getInstance();
    private String projectDatabase = configProvider.getDBName();

    public DatabaseOperations() {
        createProjectDatabase();
        useProjectDatabase();
    }

    private void createProjectDatabase() {
        LOGGER.info("Creating database: " + projectDatabase);

        String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + projectDatabase;
        try (Statement statement = connection.createStatement()) {

            statement.execute(createDatabaseQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void useProjectDatabase() {
        LOGGER.info("Switching to database: " + projectDatabase);

        String useDatabaseQuery = "USE " + projectDatabase;
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

}
