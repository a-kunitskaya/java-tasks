package com.kunitskaya.module8.service.database;

import com.kunitskaya.module9.highload.ColumnTypes;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class SqlQueryBuilder {
    private StringBuilder stringBuilder;

    public SqlQueryBuilder select() {
        stringBuilder = new StringBuilder().append("SELECT ");
        return this;
    }

    public SqlQueryBuilder select(String column) {
        stringBuilder = new StringBuilder("SELECT ").append(column)
                                                    .append(" ");
        return this;
    }

    public SqlQueryBuilder insert(String table, String... values) {
        stringBuilder = new StringBuilder("INSERT INTO ").append(table)
                                                         .append(" VALUES(");

        for (int i = 0; i < values.length; i++) {
            stringBuilder.append("\'")
                         .append(values[i]);

            if (i != values.length - 1) {
                stringBuilder.append("\',");
            } else {

                stringBuilder.append("\'");
            }
        }

        stringBuilder.append(")");
        return this;
    }

    public SqlQueryBuilder insertPrepared(String table, String... values) {
        stringBuilder = new StringBuilder().append("INSERT INTO ")
                                           .append(table)
                                           .append(" VALUES(");

        for (int i = 0; i < values.length; i++) {
            stringBuilder.append("?");

            if (i != values.length - 1) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(")");
            }
        }
        return this;
    }

    public SqlQueryBuilder delete(String table) {
        stringBuilder = new StringBuilder("DELETE FROM ").append(table);
        return this;
    }

    public SqlQueryBuilder from(String column) {
        stringBuilder.append("FROM ")
                     .append(column)
                     .append(" ");
        return this;
    }

    public SqlQueryBuilder
    where(String condition) {
        stringBuilder.append("WHERE ")
                     .append(condition)
                     .append(" ");
        return this;
    }

    public SqlQueryBuilder count(String column) {
        stringBuilder.append("COUNT(")
                     .append(column)
                     .append(") ");
        return this;
    }

    public SqlQueryBuilder distinct(String column) {
        stringBuilder.append("DISTINCT ")
                     .append(column)
                     .append(" ");
        return this;
    }

    public SqlQueryBuilder join(String column) {
        stringBuilder.append("JOIN ")
                     .append(column)
                     .append(" ");
        return this;
    }

    public SqlQueryBuilder on(String column1, String value1, String column2, String value2) {
        stringBuilder.append("ON ")
                     .append(column1.concat(".").concat(value1))
                     .append("=")
                     .append(column2.concat(".").concat(value2))
                     .append(" ");
        return this;
    }

    public SqlQueryBuilder and(String condition) {
        stringBuilder.append("AND ")
                     .append(condition);
        return this;
    }

    public SqlQueryBuilder having(String condition) {
        stringBuilder.append("HAVING ")
                     .append(condition);
        return this;
    }

    public SqlQueryBuilder groupBy(String column) {
        stringBuilder.append("GROUP BY ")
                     .append(column)
                     .append(" ");
        return this;
    }

    /**
     * Creates table with columns of random types
     *
     * @param tableName    name of the table
     * @param columnsCount how many columns the table should have
     * @param columnNames  names of the table columns
     * @return SqlQueryBuilder with query
     */
    public SqlQueryBuilder createTable(String tableName, int columnsCount, List<String> columnNames) {

        stringBuilder = new StringBuilder().append("CREATE TABLE IF NOT EXISTS ")
                                           .append(tableName)
                                           .append("(");

        for (int i = 0; i < columnsCount; i++) {
            int randomTypeIndex = RandomUtils.nextInt(0, ColumnTypes.values().length);
            ColumnTypes type = ColumnTypes.values()[randomTypeIndex]; //get random column type
            String columnType = type.name();

            if (type == ColumnTypes.VARCHAR) {
                int charactersCount = RandomUtils.nextInt(1, 100);
                columnType = type.name() + "(" + charactersCount + ")";
            }

            stringBuilder.append(columnNames.get(i))
                         .append(" ")
                         .append(columnType);
            if (i != columnsCount - 1) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(")");
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}

