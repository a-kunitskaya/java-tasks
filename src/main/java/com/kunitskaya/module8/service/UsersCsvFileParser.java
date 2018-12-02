package com.kunitskaya.module8.service;

import com.kunitskaya.module8.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsersCsvFileParser implements CSVFileParser<User> {


    @Override
    public List<User> parseToObject(String filePath) {
        List<User> users = new ArrayList<>();

        File usersFile = new File(filePath);
        try (
                InputStream inputStream = new FileInputStream(usersFile);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            users = bufferedReader.lines()
                                  .map(l -> {
                                              String[] lines = l.split(",");
                                              User user = new User();
                                              user.setId(Integer.parseInt(lines[0]));
                                              user.setName(lines[1]);
                                              user.setSurname(lines[2]);

                                              String pattern = "yyyy-MM-dd";
                                              Date birthDate = DateTimeUtil.getDateFromString(lines[3], pattern);
                                              user.setBirthDate(birthDate);
                                              return user;
                                          }
                                  )
                                  .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}


//
//String[] p = line.split(COMMA);// a CSV has comma separated lines
//    YourJavaItem item = new YourJavaItem();
//  item.setItemNumber(p[0]);//<-- this is the first column in the csv file
//          if (p[3] != null && p[3].trim().length() > 0) {
//          item.setSomeProeprty(p[3]);
//          }
//          //more initialization goes here
//          return item;
//