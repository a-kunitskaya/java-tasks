package com.kunitskaya.module9.highload;

import com.kunitskaya.module8.service.database.SqlQueryBuilder;
import com.kunitskaya.module8.service.database.operations.DatabaseOperations;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class HighloadDatabaseOperations extends DatabaseOperations {

    private SqlQueryBuilder queryBuilder = new SqlQueryBuilder();
    private static final String ADD_TO_BATCH_MESSAGE = "Adding query to batch: ";
    private static final String BATCH_SUCCESSFUL_MESSAGE = " Successfully executed batch";

    public HighloadDatabaseOperations() {
        super();
    }

    public void createRandomTables(int tablesCount, Connection... connections) {
        List<String> queries = getCreateTableQueries(tablesCount);

        Arrays.stream(connections)
              .forEach(connection -> {
                  try (Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
                      queries.stream()
                             .peek(q -> LOGGER.info(ADD_TO_BATCH_MESSAGE + q))
                             .forEach(q -> {
                                 try {
                                     statement.addBatch(q);
                                 } catch (SQLException e) {
                                     e.printStackTrace();
                                 }
                             });

                      statement.executeBatch();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
                  LOGGER.info(BATCH_SUCCESSFUL_MESSAGE);
              });
    }

    private List<String> getCreateTableQueries(int tablesCount) {
        List<String> queries = new ArrayList<>();

        IntStream.range(0, tablesCount)
                 .mapToObj(i -> RandomStringUtils.randomAlphabetic(1, 10))
                 .forEachOrdered(tableName -> {
                     int columnsCount = RandomUtils.nextInt(2, 8);
                     List<String> columns = getRandomColumns(columnsCount);
                     String query = queryBuilder.createTable(tableName, columnsCount, columns).toString();
                     queries.add(query);
                 });
        return queries;
    }

    private List<String> getRandomColumns(int columnsCount) {
        return IntStream.range(0, columnsCount)
                        .mapToObj(i -> RandomStringUtils.randomAlphanumeric(3, 7))
                        .collect(Collectors.toList());
    }
}
