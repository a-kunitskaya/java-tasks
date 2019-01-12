package com.kunitskaya;

import com.kunitskaya.module8.ConfigProvider;
import com.kunitskaya.module9.AppContext;
import com.kunitskaya.module9.database.HighloadDatabaseOperations;
import com.kunitskaya.module9.entity.OneDimensionalArray;
import com.kunitskaya.module9.service.JsonMapper;
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
        String arrayOneDJson = Paths.get("src", "main", "resources", "module8", "1d_array.json").toString();
        JsonMapper<OneDimensionalArray> jsonMapper = new JsonMapper<>();
        OneDimensionalArray oneDimensionalArray = jsonMapper.mapFromJson(arrayOneDJson, OneDimensionalArray.class);

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

        //2.	It creates m random rows for the i - th table, where m is an i - th element of M.
        // M is an N-dimensional array predefined by a user of this tool.
        database.populateTableFromArray(oneDimensionalArray, connection1, connection2);
    }
}

