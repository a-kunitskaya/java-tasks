package com.kunitskaya.service.module3;

import java.util.List;
import java.util.stream.Stream;

public interface StreamObjectsFinder<T> {
    List<T> find(Stream<T> objects);
}
