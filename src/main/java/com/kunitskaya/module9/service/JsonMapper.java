package com.kunitskaya.module9.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunitskaya.module9.entity.HighloadOneDimensionalArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonMapper<T> {

    public T mapFromJson(String path, Class<T> clazz) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        T instance = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(bytes, clazz);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
