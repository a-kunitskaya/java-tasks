package com.kunitskaya.module3.service.interfaces;

import java.util.Objects;
import java.util.function.Function;

public interface ThreeFunction<T, U, V, R> {

    static void printStartMessage(String t, String u, String v) {
        String message = "Executing ThreeFunction on objects: %s, %s, %s";
        System.out.println(String.format(message, t, u, v));
    }

    static void printFinishMessage() {
        String message = "ThreeFunction execution is finished...";
        System.out.println(message);
    }

    R apply(T t, U u, V v);

    default <S> ThreeFunction<T, U, V, S> andThen(Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }

    default Integer multiply(Integer x, Integer y, Integer z) {
        return x * y * z;
    }

}
