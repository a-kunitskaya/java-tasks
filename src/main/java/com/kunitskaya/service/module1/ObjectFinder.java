package com.kunitskaya.service.module1;

import java.util.List;

public interface ObjectFinder<T> {
    T find(List<T> objects);
}
