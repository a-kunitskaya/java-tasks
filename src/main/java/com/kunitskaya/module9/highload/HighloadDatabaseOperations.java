package com.kunitskaya.module9.highload;

import com.kunitskaya.module8.service.database.SqlQueryBuilder;
import com.kunitskaya.module8.service.database.operations.DatabaseOperations;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class HighloadDatabaseOperations extends DatabaseOperations {

    private SqlPreparedQueryBuilder preparedQueryBuilder = new SqlPreparedQueryBuilder();
    private SqlQueryBuilder queryBuilder = new SqlQueryBuilder();

    public HighloadDatabaseOperations() {
        super();
    }

    public void createTable(int tablesCount, String tableName, int columnsCount) {

        List<String> columns = new ArrayList<>();

        for (int i = 0; i < columnsCount; i++) {
            String columnName = RandomStringUtils.randomAlphanumeric(3, 7);
            columns.add(columnName);
        }

        String query = queryBuilder.createTable(tableName, columnsCount, columns).toString();

        System.out.println(query);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        ) {


            System.out.println(preparedStatement);

            preparedStatement.addBatch();
            int[] affectedRows = preparedStatement.executeBatch();
            LOGGER.info("Affected rows: " + affectedRows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//CREATE TABLE IF NOT EXISTS ? (? VARCHAR(19), ? VARCHAR(16), ? INTEGER, ? VARCHAR(54), ? INTEGER, ? BOOLEAN)