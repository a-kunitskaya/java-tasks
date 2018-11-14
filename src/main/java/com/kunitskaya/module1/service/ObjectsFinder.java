package com.kunitskaya.module1.service;

import java.util.List;

@FunctionalInterface
public interface ObjectsFinder<T> {
    List<T> find(List<T> objects) throws Exception;
}
