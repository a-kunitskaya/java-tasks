package com.kunitskaya.module8;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

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
                stringBuilder.append("\',")
                             .append(" ");
            } else {

                stringBuilder.append("\'");
            }
        }

        stringBuilder.append(")");

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

    public SqlQueryBuilder where(String condition) {
        stringBuilder.append("WHERE ")
                     .append(condition)
                     .append(" ");
        return this;
    }

    public SqlQueryBuilder count(String column) {
        stringBuilder.append(" COUNT(")
                     .append(column)
                     .append(") ");

        return this;
    }

    @Override
    public String toString() {
        String query = stringBuilder.toString();
        LOGGER.info("Executing query: " + query);
        return query;
    }
}
