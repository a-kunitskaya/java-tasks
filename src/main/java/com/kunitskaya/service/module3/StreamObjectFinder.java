package com.kunitskaya.service.module3;

import java.util.stream.Stream;

public interface StreamObjectFinder<T> {
    T find(Stream<T> objects);
}
