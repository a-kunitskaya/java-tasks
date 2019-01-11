package com.kunitskaya;

import com.kunitskaya.module8.ConfigProvider;
import com.kunitskaya.module8.ConnectionProvider;
import com.kunitskaya.module9.AppContext;
import com.kunitskaya.module9.database.HighloadDatabaseOperations;
import com.kunitskaya.module9.entity.HighloadOneDimensionalArray;
import com.kunitskaya.module9.service.JsonMapper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MainModule9 {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        HighloadDatabaseOperations database = context.getBean(HighloadDatabaseOperations.class);
        ConfigProvider configProvider = context.getBean(ConfigProvider.class);

        // Task 1. (20 points) Highload Writing Console Tool
        // Create a Highload Writing Console Tool that populates
        // the database (URL/Name are configuration settings)

        // 1.It creates N random tables with random unique name (or names from dictionary)
        // and K random columns with unique names with type taken from Z random types
        // 1.3.It supports the table creation/populating via L concurrent connections
        // (from different threads or from a few instances of classes running simultaneously)

      //  int tablesCount = RandomUtils.nextInt(1, 5);
       // database.createRandomTables(tablesCount, ConnectionProvider.getInstance());

        //2.	It creates m random rows for the i - th table, where m is an i - th element of M.
        // M is an N-dimensional array predefined by a user of this tool.
//        int[] oneDArray = {5, 7, 8, 23, 7, 4, 7, 7, 23, 754, 57, 21, 6};

//        database.insertRowsFromArray(oneDArray, 2);
//
//        int[][] twoDimensionalArray = {
//                {1, 34, 546, 5, 63, 25, 3, 4, 7, 8, 2, 6, 734, 8, 3},
//                {4, 5, 436, 7, 5, 6, 324, 4, 3, 235},
//                {83, 29, 4310, 111, 12, 423, 335, 12, 3, 4, 34, 3}
//        };
//
//        database.insertRowsFromArray(twoDimensionalArray, 2);
//
//        int[][][] threeDimensionalArray = {
//                {
//                        {8, 9, 10, 11, 12, 423, 5, 12, 3, 4, 34, 3},
//                        {4, 5, 436, 7, 5, 6, 3247, 4, 3, 235},
//                        {1, 25, 3, 5, 64, 7, 8, 2, 6, 734, 8, 3},
//
//                },
//                {
//                        {821, 92, 110, 111, 123, 423, 5, 212, 33, 34, 134, 33},
//                        {132, 113, 235, 3, 5, 64, 327, 8, 2, 36, 734, 8, 3},
//                }
//        };
//
//        database.insertRowsFromArray(threeDimensionalArray, 2);





        String arrayPath1 = Paths.get("src", "main", "resources", "module8", "1d_array.json").toString();
        JsonMapper<HighloadOneDimensionalArray> jsonMapper = new JsonMapper<>();
        HighloadOneDimensionalArray oneDimensionalArray = jsonMapper.mapFromJson(arrayPath1, HighloadOneDimensionalArray.class);

        String db = configProvider.getDBUrl();
        String username = configProvider.getDBUsername();
        String pwd = configProvider.getDBPassword();

        Connection connection1 = null;
        Connection connection2 = null;
        try {
            connection1 = DriverManager.getConnection(db, username, pwd);
            connection2 = DriverManager.getConnection(db, username, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        database.createRandomTables(oneDimensionalArray, connection1, connection2);
        database.populateTableFromArray(oneDimensionalArray, connection1, connection2);

//        database.deleteDatabase();
    }
}

