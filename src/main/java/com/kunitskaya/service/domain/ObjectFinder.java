package com.kunitskaya.service.domain;

import java.util.List;

public interface ObjectFinder<T> {
    T find(List<T> objects);
}
