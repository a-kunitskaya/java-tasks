package com.kunitskaya.service.domain.library;

import com.kunitskaya.domain.library.Book;

import java.util.List;

public interface ObjectFinder<T> {
    T find(List<T> objects);
}
