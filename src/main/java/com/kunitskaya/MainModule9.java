package com.kunitskaya;

import com.kunitskaya.module9.AppContext;
import com.kunitskaya.module9.highload.HighloadDatabaseOperations;
import org.apache.commons.lang3.RandomStringUtils;
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

        int tablesCount = RandomUtils.nextInt(1, 5);
        String tableName = RandomStringUtils.randomAlphabetic(1, 10);
        int columnsCount = RandomUtils.nextInt(2, 8);

        database.createTable(tablesCount, tableName, columnsCount);


//        2.	It creates m random rows for the i - th table, where m is an i - th element of M.
//M is an N-dimensional array predefined by a user of this tool.


//        3.	It supports the table creation/populating via L concurrent connections
// (from different threads or from a few instances of classes running simultaneously)


//        4.	All settings are located in a configuration file;
// the path to this file is a parameter of main function.

    }
}
