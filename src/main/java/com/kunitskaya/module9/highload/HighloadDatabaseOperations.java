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

    public void insertRowsFromArray(int[] rowsArray) {
        insertRowsFromArray(rowsArray, 1);
    }

    public void insertRowsFromArray(int[][] rowsArray, int rowsCount) {
        int index = RandomUtils.nextInt(0, rowsArray.length);
        int[] oneDimensionalArray = rowsArray[index];

        insertRowsFromArray(oneDimensionalArray, rowsCount);
    }

    public void insertRowsFromArray(int[][][] rowsArray, int rowsCount) {
        int index1 = RandomUtils.nextInt(0, rowsArray.length);
        int[][] twoDimensionalArray = rowsArray[index1];

        int index2 = RandomUtils.nextInt(0, twoDimensionalArray.length);
        int[] oneDimensionalArray = twoDimensionalArray[index2];

        insertRowsFromArray(oneDimensionalArray, rowsCount);
    }

    private void insertRowsFromArray(int[] rowsArray, int rowsCount) {
        ResultSet columnsResult = null;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            String tableName = getRandomExistingTableName(metaData);

            int columnCount = getColumnCount(tableName);
            columnsResult = getTableColumns(metaData, tableName);

            String query = sqlQueryBuilder.insertPrepared(tableName, columnCount).toString();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            addBatch(rowsArray, rowsCount, columnsResult, preparedStatement);

            int[] affectedRows = preparedStatement.executeBatch();
            LOGGER.info("Affected rows: " + affectedRows.length);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResult(columnsResult);
        }
    }

    private ResultSet getTableColumns(DatabaseMetaData metaData, String tableName) {
        try {
            return metaData.getColumns("jmp", null, tableName, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Retrieves a random table name from the tables existing in DB
     *
     * @param metaData - connection metadata to get tables from
     * @return one of the existing tables' names
     */
    private String getRandomExistingTableName(DatabaseMetaData metaData) {
        String tableName = null;

        try (ResultSet tablesResult = metaData.getTables("jmp", null, null, null);) {
            int tablesCount = 0;

            while (tablesResult.next()) {
                tablesCount++;
            }

            int tableIndex = RandomUtils.nextInt(1, tablesCount);

            tablesResult.absolute(tableIndex);
            tableName = tablesResult.getString("TABLE_NAME");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableName;
    }

    private void closeResult(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int getColumnCount(String tableName) {
        int columnCount = 0;
        String rowsCountQuery = "Describe " + tableName;
        String message = "Table: %s has column with name: %s";

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(rowsCountQuery);) {
            while (result.next()) {
                String column = result.getString(1);
                LOGGER.info(String.format(message, tableName, column));
                columnCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnCount;
    }

    private void addBatch(int[] rowsArray, int rowsCount, ResultSet columns, PreparedStatement preparedStatement) throws SQLException {

        for (int i = 0; i < rowsCount; i++) {
            int index = 0;
            columns.beforeFirst();

            while (columns.next()) {
                int dataType = Integer.parseInt(columns.getString("DATA_TYPE"));

                if (index >= rowsArray.length) {
                    throw new IllegalArgumentException("Items count in array row < table columns count");
                }

                if (dataType == ColumnTypes.VARCHAR.getCode()) {
                    preparedStatement.setString(index + 1, String.valueOf(rowsArray[index]));
                } else {
                    preparedStatement.setInt(index + 1, rowsArray[index]);
                }
                index++;
            }

            LOGGER.info("Adding statement to batch: " + preparedStatement);
            preparedStatement.addBatch();
        }
    }
}
