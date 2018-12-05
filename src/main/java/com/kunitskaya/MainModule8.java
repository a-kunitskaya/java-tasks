package com.kunitskaya;

import com.kunitskaya.module8.MyFirstConnection;
import com.kunitskaya.module8.service.database.DatabaseService;
import com.kunitskaya.module8.service.database.operations.FriendshipDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.LikeDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.PostDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.UserDatabaseOperations;

import java.time.Instant;
import java.util.Date;

public class MainModule8 {

    public static void main(String[] args) {

        //Task 3. JDBC-based Social Network
        //3.1. Create simple database with tables

        //3.1.1 Users (id, name, surname, birthdate),
        UserDatabaseOperations userDatabase = new UserDatabaseOperations();

        //3.1.2.Friendships (userid1, userid2, timestamp)
        FriendshipDatabaseOperations friendshipDatabase = new FriendshipDatabaseOperations();

        //3.1.3. Posts (id, userId, text, timestamp)
        PostDatabaseOperations postDatabase = new PostDatabaseOperations();

        //3.1.4. Likes (postid, userid, timestamp)
        LikeDatabaseOperations likeDatabase = new LikeDatabaseOperations();
        
        //TODO: delete
//         userDatabase.deleteAllUsers();
//         friendshipDatabase.deleteAllFriendships();
//         postDatabase.deleteAllPosts();
//         likeDatabase.deleteAllLikes();
        //TODO

        //Task 1. JDBC Quick Start
        // 1.3. Write MyFirstConnection class with a few methods that takes connection
        // parameters and a SQL query string (without parameters),
        // executes it via Statement and prints the given results.
        Date birthDate = Date.from(Instant.now());

        userDatabase.addUser(0, "Jack", "White", birthDate);
        userDatabase.printUsersCount();

        //1.4.	Parametrize the query from the previous subtask and use Prepared Statements to inject parameters
        userDatabase.addUserWithPreparedStatement(0, "Daniel", "McDonald", birthDate);
        userDatabase.deleteAllUsers();

        //1.5.	Add a method that prints all tables in the database
        userDatabase.printAllTables();

        // 3.2. Populate tables with data which are make sense
        // 3.2.1.> 1 000 users
        DatabaseService databaseService = new DatabaseService();
        databaseService.populateUsersTable();

        //3.2.2. > 70 000 friendships
        databaseService.populateFriendshipsTable();


        // 3.2.3. > 300 000 likes in 2025)
        databaseService.populateLikesTable(); //[AK] takes about 5 mins to run
        databaseService.populatePostsTable();

        userDatabase.printUsersCount();
        friendshipDatabase.printFriendshipsCount();
        likeDatabase.printLikesCount();
        postDatabase.printPostsCount();

        // 3. Program should print out all names (only distinct) of users
        // who has more when 100 friends and 100 likes in March 2025.

        //1. select distinct users.name from users join friendships on users.id=friendships.userId1;
        //2. for each name ->
        //select count(users.name) from users join likes on users.id=likes.userid where likes.timestamp > 2025-00-00 AND users.name = 'name';
        //3.return what returns > 100


        // userDatabase.deleteAllUsers();
        // friendshipDatabase.deleteAllFriendships();
        // postDatabase.deleteAllPosts();
        // likeDatabase.deleteAllLikes();
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

