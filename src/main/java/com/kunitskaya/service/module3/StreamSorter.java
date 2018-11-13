package com.kunitskaya.service.module3;

import java.util.stream.Stream;

public interface StreamSorter<T> {
    void sort(Stream<T> objects);
}
