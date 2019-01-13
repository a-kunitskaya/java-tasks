package com.kunitskaya.module9.database;

import com.kunitskaya.module8.ConfigProvider;
import com.kunitskaya.module8.service.database.SqlQueryBuilder;
import com.kunitskaya.module8.service.database.operations.DatabaseOperations;
import com.kunitskaya.module9.entity.HighloadConfiguration;
import com.kunitskaya.module9.entity.OneDimensionalArray;
import com.kunitskaya.module9.entity.ThreeDimensionalArray;
import com.kunitskaya.module9.entity.TwoDimensionalArray;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class HighloadDatabaseOperations extends DatabaseOperations {

    @Autowired
    private SqlQueryBuilder queryBuilder;
    @Autowired
    private ConfigProvider configProvider;

    private static final String ADD_TO_BATCH_MESSAGE = "Adding query to batch: ";
    private static final String BATCH_SUCCESSFUL_MESSAGE = " Successfully executed batch";

    public HighloadDatabaseOperations() {
        super();
    }

    public void createRandomTables(HighloadConfiguration configuration) {
        List<Connection> connections = getConnections(configuration);

        List<String> queries = getCreateTableQueries(configuration);

        connections.parallelStream()
                   .forEach(c -> {
                       try (Statement statement = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
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

    private List<Connection> getConnections(HighloadConfiguration configuration) {
        int connectionsCount = configuration.getlConnectionsCount();
        List<Connection> connections = new ArrayList<>();

        for (int i = 0; i < connectionsCount; i++) {
            try {
                Connection connection = DriverManager.getConnection(configProvider.getDBUrl(), configProvider.getDBUsername(), configProvider.getDBPassword());
                connections.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connections;
    }

    private List<String> getCreateTableQueries(HighloadConfiguration configuration) {
        List<String> queries = new ArrayList<>();
        List<String> tableNames = getRandomNames(configuration.getnTables());

        tableNames.forEach(tableName -> {
            List<String> columns = getRandomNames(configuration.getkColumnsCount());
            String query = queryBuilder.createTable(configuration, tableName, columns).toString();
            queries.add(query);
        });
        return queries;
    }

    /**
     * Composes random strings to be used as not yet existing column/tables names
     *
     * @param namesCount count of required names
     * @return random string values
     */
    private List<String> getRandomNames(int namesCount) {
        return IntStream.range(0, namesCount)
                        .mapToObj(i -> RandomStringUtils.randomAlphabetic(3, 6))
                        .collect(Collectors.toList());
    }

    /**
     * Retrieves columns of an existing table
     *
     * @param metaData  connection metadata
     * @param tableName table to retrieve columns from
     * @return table columns
     */
    private ResultSet getTableColumns(DatabaseMetaData metaData, String tableName) {
        try {
            return metaData.getColumns(DATABASE, null, tableName, null);
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

        try (ResultSet tablesResult = metaData.getTables(DATABASE, null, null, null)) {
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

    /**
     * Retrieves count of columns of an existing table
     *
     * @param tableName table to count columns in
     * @return the number of columns
     */
    private int getColumnCount(String tableName) {
        int columnCount = 0;
        String rowsCountQuery = "Describe " + tableName;
        String message = "Table: %s has column with name: %s";

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(rowsCountQuery)) {
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

    private void addBatch(ResultSet columns, PreparedStatement preparedStatement, List<int[]> rowsArrays) throws SQLException {

        for (int[] row : rowsArrays) {
            int index = 0;

            columns.beforeFirst();
            while (columns.next()) {
                int dataType = Integer.parseInt(columns.getString("DATA_TYPE"));

                if (index >= row.length) {
                    throw new IllegalArgumentException("Items count in array row < table columns count");
                }

                if (dataType == SqlTypes.VARCHAR.getCode()) {
                    preparedStatement.setString(index + 1, String.valueOf(row[index]));
                } else {
                    preparedStatement.setInt(index + 1, row[index]);
                }
                index++;
            }
            LOGGER.info(ADD_TO_BATCH_MESSAGE + preparedStatement);
            preparedStatement.addBatch();
        }
    }

    public void populateTableFromArray(HighloadConfiguration configuration) {
        List<int[]> arrays = getOneDimensionalArrayList(configuration);
        List<Connection> connections = getConnections(configuration);

        connections.parallelStream()
                   .forEach(c -> {
                       try {
                           DatabaseMetaData metaData = connection.getMetaData();
                           String tableName = getRandomExistingTableName(metaData);

                           int columnCount = getColumnCount(tableName);
                           ResultSet columnsResult = getTableColumns(metaData, tableName);

                           String query = sqlQueryBuilder.insertPrepared(tableName, columnCount).toString();
                           PreparedStatement preparedStatement = connection.prepareStatement(query);

                           addBatch(columnsResult, preparedStatement, arrays);

                           int[] affectedRows = preparedStatement.executeBatch();
                           LOGGER.info("Affected rows: " + affectedRows.length);
                       } catch (SQLException e) {
                           e.printStackTrace();
                       }
                   });

    }

    public List<int[]> getOneDimensionalArrayList(HighloadConfiguration configuration) {
        List<int[]> arrays = new ArrayList<>();
        int rowsCount = configuration.getmRowsCount();
        int[] oneDimensionalArray;

        for (int i = 0; i < rowsCount; i++) {

            if (configuration instanceof OneDimensionalArray) {
                oneDimensionalArray = ((OneDimensionalArray) configuration).getArray();

            } else if (configuration instanceof TwoDimensionalArray) {
                int[][] array = ((TwoDimensionalArray) configuration).getArray();

                int index = RandomUtils.nextInt(0, array.length);
                oneDimensionalArray = array[index];

            } else if (configuration instanceof ThreeDimensionalArray) {
                int[][][] array = ((ThreeDimensionalArray) configuration).getArray();

                int index1 = RandomUtils.nextInt(0, array.length);
                int[][] twoDimensionalArray = array[index1];

                int index2 = RandomUtils.nextInt(0, twoDimensionalArray.length);
                oneDimensionalArray = twoDimensionalArray[index2];

            } else {
                throw new IllegalArgumentException("No implementation is found for the specified array type");
            }
            arrays.add(oneDimensionalArray);
        }
        return arrays;
    }

}
