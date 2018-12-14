package com.kunitskaya;

import com.kunitskaya.module8.MyFirstConnection;
import com.kunitskaya.module9.AppContext;
import com.kunitskaya.module9.highload.HighloadDatabaseOperations;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainModule9 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        HighloadDatabaseOperations database = context.getBean(HighloadDatabaseOperations.class);

        // Task 1. (20 points) Highload Writing Console Tool
        // Create a Highload Writing Console Tool that populates
        // the database (URL/Name are configuration settings)

        // 1.It creates N random tables with random unique name (or names from dictionary)
        // and K random columns with unique names with type taken from Z random types
        // 1.3.It supports the table creation/populating via L concurrent connections
        // (from different threads or from a few instances of classes running simultaneously)

        int tablesCount = RandomUtils.nextInt(1, 5);
        database.createRandomTables(tablesCount, MyFirstConnection.getInstance());

        //2.	It creates m random rows for the i - th table, where m is an i - th element of M.
        // M is an N-dimensional array predefined by a user of this tool.
        int[] oneDimensionalArray = {5, 7, 8, 23, 7, 4, 7, 7, 23, 754, 57, 21, 6};

        database.insertRowsFromArray(oneDimensionalArray);

        int[][] twoDimensionalArray = {
                {1, 25, 3, 5, 64, 7, 8, 2, 6, 734, 8, 3},
                {4, 5, 436, 7, 5, 6, 324, 4, 3, 235},
                {8, 9, 10, 11, 12, 423, 5, 12, 3, 4, 34, 3}
        };

        database.insertRowsFromArray(twoDimensionalArray, 2);

        int[][][] threeDimensionalArray = {
                {
                        {8, 9, 10, 11, 12, 423, 5, 12, 3, 4, 34, 3},
                        {4, 5, 436, 7, 5, 6, 3247, 4, 3, 235},
                        {1, 25, 3, 5, 64, 7, 8, 2, 6, 734, 8, 3},

                },
                {
                        {8, 9, 10, 11, 12, 423, 5, 12, 3, 4, 34, 3},
                        {1, 1, 25, 3, 5, 64, 7, 8, 2, 6, 734, 8, 3},
                }
        };

        database.insertRowsFromArray(threeDimensionalArray, 2);
    }
}

