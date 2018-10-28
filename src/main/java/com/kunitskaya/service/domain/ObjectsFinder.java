package com.kunitskaya.service.domain;

import java.util.List;

@FunctionalInterface
public interface ObjectsFinder<T> {
    List<T> find(List<T> objects) throws Exception;
}
