package com.kunitskaya.service.interfaces;

import java.util.Objects;
import java.util.function.Function;

public interface ThreeFunction<T, U, V, R> {

    R apply(T t, U u, V v);

    default <S> ThreeFunction<T, U, V, S> andThen(Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }
}
