package com.kunitskaya.module8.service.database;

import com.kunitskaya.module8.domain.Friendship;
import com.kunitskaya.module8.domain.Like;
import com.kunitskaya.module8.domain.Post;
import com.kunitskaya.module8.domain.User;
import com.kunitskaya.module8.service.database.operations.FriendshipDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.LikeDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.PostDatabaseOperations;
import com.kunitskaya.module8.service.database.operations.UserDatabaseOperations;
import com.kunitskaya.module8.service.files.*;

import java.nio.file.Paths;
import java.util.List;

public class DatabaseService {
    private UserDatabaseOperations userDatabase;
    private FriendshipDatabaseOperations friendshipDatabase;
    private LikeDatabaseOperations likeDatabase;
    private PostDatabaseOperations postDatabase;

    public DatabaseService() {

        userDatabase = new UserDatabaseOperations();
        friendshipDatabase = new FriendshipDatabaseOperations();
        likeDatabase = new LikeDatabaseOperations();
        postDatabase = new PostDatabaseOperations();
    }

    private static final String USERS_FILE_PATH = Paths.get("src", "main", "resources", "module8", "users.csv").toString();
    private static final String FRIENDSHIPS_FILE_PATH = Paths.get("src", "main", "resources", "module8", "friendships.csv").toString();
    private static final String LIKES_FILE_PATH = Paths.get("src", "main", "resources", "module8", "likes.csv").toString();
    private static final String POSTS_FILE_PATH = Paths.get("src", "main", "resources", "module8", "posts.csv").toString();


    public void populateUsersTable() {
        for (int i = 0; i < 101; i++) {
            CSVFileParser<User> parser = new UsersCSVFileParser();
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
        for (int i = 0; i < 7001; i++) {
            CSVFileParser<Friendship> parser = new FriendshipsCSVFileParser();
            List<Friendship> friendships = parser.parseToObject(FRIENDSHIPS_FILE_PATH);
            for (int j = 0; j < friendships.size(); j++) {
                Friendship friendship = friendships.get(j);
                friendshipDatabase.addFriendship(friendship);
            }
        }
    }

    public void populateLikesTable() {
        for (int i = 0; i < 30001; i++) {
            CSVFileParser<Like> parser = new LikesCSVFileParser();
            List<Like> likes = parser.parseToObject(LIKES_FILE_PATH);
            for (int j = 0; j < likes.size(); j++) {
                Like like = likes.get(j);
                likeDatabase.addLike(like);
            }
        }
    }

    public void populatePostsTable() {
        CSVFileParser<Post>parser = new PostsCSVFileParser();
        List<Post> posts = parser.parseToObject(POSTS_FILE_PATH);
        for(int j = 0; j < posts.size(); j++){
            Post post = posts.get(j);
            postDatabase.addPost(post);
        }
    }
}
