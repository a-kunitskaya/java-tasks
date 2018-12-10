package com.kunitskaya.module8.service.files;

import com.kunitskaya.module2.annotations.ThisCodeSmells;

import java.util.List;

public interface CSVFileParser<T> {
    List<T> parseToObject(String filePath);
}
