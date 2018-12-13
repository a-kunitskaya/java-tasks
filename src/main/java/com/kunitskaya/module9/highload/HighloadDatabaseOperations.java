package com.kunitskaya.module9.highload;

import com.kunitskaya.module8.service.database.SqlQueryBuilder;
import com.kunitskaya.module8.service.database.operations.DatabaseOperations;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;
import static java.sql.Types.VARCHAR;

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
        List<String> tableNames = getRandomTableNames(tablesCount);

        tableNames.forEach(tableName -> {
            int columnsCount = RandomUtils.nextInt(2, 8);
            List<String> columns = getRandomColumnsNames(columnsCount);
            String query = queryBuilder.createTable(tableName, columnsCount, columns).toString();
            queries.add(query);
        });
        return queries;
    }

    private List<String> getRandomColumnsNames(int columnsCount) {
        return IntStream.range(0, columnsCount)
                        .mapToObj(i -> RandomStringUtils.randomAlphanumeric(3, 7))
                        .collect(Collectors.toList());
    }

    private List<String> getRandomTableNames(int tablesCount) {
        return IntStream.range(0, tablesCount)
                        .mapToObj(i -> RandomStringUtils.randomAlphabetic(1, 10))
                        .collect(Collectors.toList());
    }

    public void insertRandomRowsIntoAnyTable(int[][] twoDimensionalArray) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tablesResult = metaData.getTables("jmp", null, null, null);

            if (tablesResult.next()) {
                String tableName = tablesResult.getString("TABLE_NAME");
                ResultSet columnsResult = metaData.getColumns("jmp", null, tableName, null);
                String query = sqlQueryBuilder.insertPrepared(tableName, columnCount).toString();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                while (columnsResult.next()) {
                    int dataType = Integer.parseInt(columnsResult.getString("DATA_TYPE"));

                    if(dataType == ColumnTypes.VARCHAR.getCode()){
                       // preparedStatement.setString();
                    }

                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//любое кол-во строк из массива в любой таблице
