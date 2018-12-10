package com.kunitskaya.module8.service.files;

import com.kunitskaya.module8.domain.Like;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LikesCSVFileParser implements CSVFileParser<Like> {
    @Override
    public List<Like> parseToObject(String filePath) {
        List<Like> likes = new ArrayList<>();
        File file = new File(filePath);

        try (
                InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            likes = bufferedReader.lines()
                                  .map(l -> {
                                              String[] values = l.split(",");
                                              Like like = new Like();

                                              like.setPostId(Integer.parseInt(values[0]));
                                              like.setUserId(Integer.parseInt(values[1]));
                                              like.setTimestamp(Timestamp.valueOf(values[2]));
                                              return like;
                                          }
                                  )
                                  .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return likes;
    }
}
