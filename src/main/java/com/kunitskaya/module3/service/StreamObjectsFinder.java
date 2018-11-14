package com.kunitskaya.module3.service;

import java.util.List;
import java.util.stream.Stream;

public interface StreamObjectsFinder<T> {
    List<T> find(Stream<T> objects);
}
