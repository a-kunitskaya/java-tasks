package com.kunitskaya.module1.service;

import java.util.List;

public interface ObjectFinder<T> {
    T find(List<T> objects);
}
