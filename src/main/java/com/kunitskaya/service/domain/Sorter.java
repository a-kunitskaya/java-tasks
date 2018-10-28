package com.kunitskaya.service.domain;

import java.util.List;

public interface Sorter<T> {
    void sort(List<T> objects);
}
