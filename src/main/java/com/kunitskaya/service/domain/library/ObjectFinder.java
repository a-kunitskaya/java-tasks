package com.kunitskaya.service.domain.library;

import java.util.List;

public interface ObjectFinder<T> {
    T find(List<T> objects);
}
