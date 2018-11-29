package com.kunitskaya;

import com.kunitskaya.module8.ConfigProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainModule8 {
    private static ConfigProvider configProvider = new ConfigProvider();

    private static String db = configProvider.getDBUrl();
    private static String username = configProvider.getDBUsername();
    private static String password = configProvider.getDBPassword();

    public static void main(String[] args) {


        try {
            Connection connection = DriverManager.getConnection(db, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
