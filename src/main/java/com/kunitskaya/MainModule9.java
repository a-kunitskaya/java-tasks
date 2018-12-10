package com.kunitskaya;

import com.kunitskaya.module9.highload.ColumnTypes;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class MainModule9 {

    public static void main(String[] args) {


//        Task 1. (20 points) Highload Writing Console Tool
// Create a Highload Writing Console Tool that populates
// the database (URL/Name are configuration settings)
//        1.	It creates N random tables with random unique name (or names from dictionary)
// and K random columns with unique names with type taken from Z random types
        String query = "CREATE TABLE IF NOT EXISTS ? (? INT NOT NULL AUTO_INCREMENT, name VARCHAR(30))";


        int numberOfTables = RandomUtils.nextInt(1, 5);
        String tableName = RandomStringUtils.randomAlphabetic(1, 10);


        for (int i = 0; i < numberOfTables; i++) {

            int numberOfColumns = RandomUtils.nextInt(1, 8);


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
            System.out.println(stringBuilder.toString());
        }


//        2.	It creates m random rows for the i - th table, where m is an i - th element of M.
//M is an N-dimensional array predefined by a user of this tool.


//        3.	It supports the table creation/populating via L concurrent connections
// (from different threads or from a few instances of classes running simultaneously)


//        4.	All settings are located in a configuration file;
// the path to this file is a parameter of main function.

    }
}
