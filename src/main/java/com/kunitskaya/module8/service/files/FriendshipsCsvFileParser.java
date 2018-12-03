package com.kunitskaya.module8.service.files;

import com.kunitskaya.module8.domain.Friendship;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FriendshipsCsvFileParser implements CSVFileParser<Friendship> {

    @Override
    public List<Friendship> parseToObject(String filePath) {
        List<Friendship> friendships = new ArrayList<>();
        File file = new File(filePath);

        try (
                InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            friendships = bufferedReader.lines()
                                        .map(l -> {
                                                    String[] values = l.split(",");
                                                    Friendship friendship = new Friendship();
                                                    friendship.setUserId1(Integer.parseInt(values[0]));
                                                    friendship.setUserId2(Integer.parseInt(values[1]));
                                                    friendship.setTimestamp(Timestamp.valueOf(values[2]));
                                                    return friendship;
                                                }
                                        )
                                        .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return friendships;
    }
}
