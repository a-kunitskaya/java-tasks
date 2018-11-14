package com.kunitskaya.module3.service;

import java.util.stream.Stream;

public interface StreamObjectFinder<T> {
    T find(Stream<T> objects);
}
