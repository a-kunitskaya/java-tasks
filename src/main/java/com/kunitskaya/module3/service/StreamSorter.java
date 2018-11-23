package com.kunitskaya.module3.service;

import java.util.stream.Stream;

public interface StreamSorter<T> {
    void sort(Stream<T> objects);
}
