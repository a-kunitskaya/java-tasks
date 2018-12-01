package com.kunitskaya;

import com.kunitskaya.module8.*;


import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;

public class MainModule8 {

    public static void main(String[] args) {

        //Task 1. JDBC Quick Start
        // 1.3. Write MyFirstConnection class with a few methods that takes connection
        // parameters and a SQL query string (without parameters),
        // executes it via Statement and prints the given results.
        UsersDatabaseOperations usersDatabase = new UsersDatabaseOperations();

        Date birthDate = Date.from(Instant.now());

        usersDatabase.addUser(0, "Jack", "White", birthDate);
        usersDatabase.getUsersCount();

        //1.4.	Parametrize the query from the previous subtask and use Prepared Statements to inject parameters
        usersDatabase.addUserWithPreparedStatement(1, "Daniel", "McDonald", birthDate);

        //1.5.	Add a method that prints all tables in the database
        usersDatabase.printAllTables();

        //Task 3. JDBC-based Social Network
        //3.1. Create simple database with tables
        // Users (id, name, surname, birthdate),


        // Friendships (userid1, userid2, timestamp),
        // Posts (id, userId, text, timestamp),
        // Likes (postid, userid, timestamp).
//
//2. Populate tables with data which are make sense (> 1 000 users, > 70 000 friendships, > 300 000 likes in 2025)*
//
//3. Program should print out all names (only distinct) of users who has more when 100 friends and 100 likes in March 2025.
//
//Implement all actions (table creation, insert data and reading) with JDBC.
//
//Protect JDBC operations from SQL – injections with Prepared Statements.
//
//* - you could prepare dictionaries (maps) in memory (with usernames for example) or data in files to generate data for the populating process

        usersDatabase.deleteAllUsers();
        MyFirstConnection.closeConnection();
    }
}


//
//
//Task 2. (20 points) Stored Procedure
//
//Take the existing (or write from zero) JDBC solution with a few CRUD operations and more complex SQL (for simple report generation) and migrate it to stored procedures.*
//
//1.	Write SQL – script to create those stored procedures with Java style parameters and specific external names. **
//
//2.	Write a test which drops all stored procedures and creates a few of them via Java code
//
//3.	Also, provide the script to print out all stored procedure in your database and dropping them for test purposes, for example.
//
//4.	Check that the application works properly, all test are green and so on.
//
//5.	Compare the performance of two solution; explain to your mentor the benefits or disadvantages of storage procedure usage for the taken application.
//
//* 3-5 tables with CRUD operations and two complex SELECTS can be enough.
//** Use MySQL or PostgreSQL or Oracle
//
//

