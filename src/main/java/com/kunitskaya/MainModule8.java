package com.kunitskaya;

import com.kunitskaya.module8.MyFirstConnection;
import com.kunitskaya.module8.service.database.DatabaseService;
import com.kunitskaya.module8.service.database.operations.FriendshipDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.LikeDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.PostDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.UserDatabaseOperations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class MainModule8 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("module8/beans.xml");

        //Task 3. JDBC-based Social Network
        //3.1. Create simple database with tables
        //3.1.1 Users (id, name, surname, birthdate),
        UserDatabaseOperations usersDatabase = context.getBean("users_db", UserDatabaseOperations.class);

        //3.1.2.Friendships (userid1, userid2, timestamp)
        FriendshipDatabaseOperations friendshipsDatabase = context.getBean("friendships_db", FriendshipDatabaseOperations.class);

        //3.1.3. Posts (id, userId, text, timestamp)
        PostDatabaseOperations postsDatabase = context.getBean("posts_db", PostDatabaseOperations.class);

        //3.1.4. Likes (postid, userid, timestamp)
        LikeDatabaseOperations likesDatabase = context.getBean("likes_db", LikeDatabaseOperations.class);

        //1.5.	Add a method that prints all tables in the database
        usersDatabase.printAllTables();

        //Task 1. JDBC Quick Start
        // 1.3. Write MyFirstConnection class with a few methods that takes connection
        // parameters and a SQL query string (without parameters),
        // executes it via Statement and prints the given results.
        Date birthDate = Date.from(Instant.now());
        usersDatabase.addUser(0, "Jack", "White", birthDate);
        usersDatabase.printCount();

        //1.4.	Parametrize the query from the previous subtask and use Prepared Statements to inject parameters
        usersDatabase.addUserWithPreparedStatement(0, "Daniel", "McDonald", birthDate);
        usersDatabase.printCount();

        usersDatabase.deleteFrom();

        // 3.2. Populate tables with data which are make sense
        // 3.2.1.> 1 000 users
        DatabaseService databaseService = context.getBean("db_service", DatabaseService.class);
        databaseService.populateUsersTable();

        //3.2.2. > 70 000 friendships
        databaseService.populateFriendshipsTable();

        // 3.2.3. > 300 000 likes in 2025)
        databaseService.populateLikesTable(); //[AK] takes about 7 mins to perform
        databaseService.populatePostsTable();

        usersDatabase.printCount();
        friendshipsDatabase.printCount();
        likesDatabase.printCount();
        postsDatabase.printCount();

        // 3. Program should print out all names (only distinct) of users
        // who has more when 100 friends and 100 likes in March 2025.
        List<String> popularUsers = usersDatabase.getPopularUsers("2025-03-00", 100, 100);
        LOGGER.info("Popular users: " + popularUsers.toString());

        usersDatabase.deleteFrom();
        friendshipsDatabase.deleteFrom();
        postsDatabase.deleteFrom();
        likesDatabase.deleteFrom();

        MyFirstConnection.closeConnection();
    }
}
