package com.kunitskaya;

import com.kunitskaya.logging.ProjectLogger;
import com.kunitskaya.module8.ConfigProvider;
import com.kunitskaya.module9.AppContext;
import com.kunitskaya.module9.database.HighloadDatabaseOperations;
import com.kunitskaya.module9.entity.OneDimensionalArray;
import com.kunitskaya.module9.entity.ThreeDimensionalArray;
import com.kunitskaya.module9.entity.TwoDimensionalArray;
import com.kunitskaya.module9.service.JsonMapper;
import org.apache.logging.log4j.message.StringMapMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.file.Paths;
import java.sql.Connection;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;


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
        String oneDimensionalArrayPath = Paths.get("src", "main", "resources", "module9", "1d_array.json").toString();
        JsonMapper<OneDimensionalArray> jsonMapper1 = new JsonMapper<>();
        OneDimensionalArray oneDimensionalArray = jsonMapper1.mapFromJson(oneDimensionalArrayPath, OneDimensionalArray.class);

        database.createRandomTables(oneDimensionalArray);

        //2.	It creates m random rows for the i - th table, where m is an i - th element of M.
        // M is an N-dimensional array predefined by a user of this tool.
        String populateTableMessage = "Populating table from array of '%s' dimensions";
        LOGGER.info(String.format(populateTableMessage, 1));
        database.populateTableFromArray(oneDimensionalArray);

        String twoDimensionalArrayPath = Paths.get("src", "main", "resources", "module9", "2d_array.json").toString();
        JsonMapper<TwoDimensionalArray> jsonMapper2 = new JsonMapper<>();
        TwoDimensionalArray twoDimensionalArray = jsonMapper2.mapFromJson(twoDimensionalArrayPath, TwoDimensionalArray.class);

        LOGGER.info(String.format(populateTableMessage, 2));
        database.populateTableFromArray(twoDimensionalArray);

        String threeDimensionalArrayPath = Paths.get("src", "main", "resources", "module9", "3d_array.json").toString();
        JsonMapper<ThreeDimensionalArray> jsonMapper3 = new JsonMapper<>();
        ThreeDimensionalArray threeDimensionalArray = jsonMapper3.mapFromJson(threeDimensionalArrayPath, ThreeDimensionalArray.class);

        LOGGER.info(String.format(populateTableMessage, 3));
        database.populateTableFromArray(threeDimensionalArray);
    }
}

