package com.kunitskaya.module9.highload;

import org.apache.commons.lang3.RandomUtils;

public class SqlPreparedQueryBuilder {
    private StringBuilder stringBuilder;


    /**
     * Creates table with random type columns
     *
     * @param numberOfColumns columns count
     * @return this class instance;
     */
    public String getCreateTableQuery(int numberOfColumns) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ? (");

        for (int j = 0; j < numberOfColumns; j++) {
            int randomTypeIndex = RandomUtils.nextInt(0, ColumnTypes.values().length);
            ColumnTypes type = ColumnTypes.values()[randomTypeIndex];
            String columnType = type.name();

            if (type == ColumnTypes.VARCHAR) {
                int charactersCount = RandomUtils.nextInt(1, 100);
                columnType = type.name() + "(" + charactersCount + ")";
            }

            stringBuilder.append("? ")
                         .append(columnType);
            if (j != numberOfColumns - 1) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(")");
            }
        }
        return stringBuilder.toString();
    }
}
