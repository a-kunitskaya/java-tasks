package com.kunitskaya.module8.service.database;

import com.kunitskaya.module8.domain.Friendship;
import com.kunitskaya.module8.domain.User;
import com.kunitskaya.module8.service.database.operations.FriendshipDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.UserDatabaseOperations;
import com.kunitskaya.module8.service.files.CSVFileParser;
import com.kunitskaya.module8.service.files.FriendshipsCsvFileParser;
import com.kunitskaya.module8.service.files.UsersCsvFileParser;

import java.nio.file.Paths;
import java.util.List;

public class DatabaseService {
    private UserDatabaseOperations userDatabase;
    private FriendshipDatabaseOperations friendshipDatabase;

    public DatabaseService() {

        userDatabase = new UserDatabaseOperations();
        friendshipDatabase = new FriendshipDatabaseOperations();
    }

    private static final String USERS_FILE_PATH = Paths.get("src", "main", "resources", "module8", "users.csv").toString();
    private static final String FRIENDSHIPS_FILE_PATH = Paths.get("src", "main", "resources", "module8", "friendships.csv").toString();

    public void populateUsersTable() {
        for (int i = 0; i < 101; i++) {
            CSVFileParser<User> parser = new UsersCsvFileParser();
            List<User> users = parser.parseToObject(USERS_FILE_PATH);
            for (int j = 0; j < users.size(); j++) {
                User user = users.get(j);
                int id = Integer.parseInt(String.valueOf(i) + String.valueOf(j));
                user.setId(id);
                userDatabase.addUser(user);
            }
        }
    }

    public void populateFriendshipsTable() {
        for (int i = 0; i < 5001; i++) {
            CSVFileParser<Friendship> parser = new FriendshipsCsvFileParser();
            List<Friendship> friendships = parser.parseToObject(FRIENDSHIPS_FILE_PATH);
            for (int j = 0; j < friendships.size(); j++) {
                Friendship friendship = friendships.get(j);
                friendshipDatabase.addFriendship(friendship);
            }
        }
    }
}
