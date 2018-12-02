package com.kunitskaya.module8.service;

import com.kunitskaya.module8.domain.User;
import com.kunitskaya.module8.service.database.operations.UserDatabaseOperations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    private UserDatabaseOperations userDatabase;

    public UserService() {
      userDatabase = new UserDatabaseOperations();
    }

    private static final String USERS_FILE_PATH = "src/main/resources/module8/users.csv";

    public void populateUsersTable() {
        for (int i = 0; i < 100; i++) {
            UsersCsvFileParser usersCsvParser = new UsersCsvFileParser();
            List<User> users = usersCsvParser.parseToObject(USERS_FILE_PATH);
            for (int j = 0; j < users.size(); j++) {
                User user = users.get(j);
                int id = Integer.parseInt(String.valueOf(i) + String.valueOf(j));
                user.setId(id);
                userDatabase.addUser(user);
            }
        }
    }
}
