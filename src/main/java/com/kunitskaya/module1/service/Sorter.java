package com.kunitskaya.module1.service;

import java.util.List;

public interface Sorter<T> {
    void sort(List<T> objects);
}
