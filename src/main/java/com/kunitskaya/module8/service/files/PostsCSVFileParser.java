package com.kunitskaya.module8.service.files;

import com.kunitskaya.module8.domain.Post;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostsCSVFileParser implements CSVFileParser<Post> {

    @Override
    public List<Post> parseToObject(String filePath) {
        List<Post> posts = new ArrayList<>();
        File file = new File(filePath);

        try (
                InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            posts = bufferedReader.lines()
                                  .map(l -> {
                                              String[] values = l.split(",");
                                              Post post = new Post();
                                              post.setId(Integer.parseInt(values[0]));
                                              post.setUserId(Integer.parseInt(values[1]));
                                              post.setText(values[2]);
                                              post.setTimestamp(Timestamp.valueOf(values[3]));
                                              return post;
                                          }
                                  )
                                  .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }
}