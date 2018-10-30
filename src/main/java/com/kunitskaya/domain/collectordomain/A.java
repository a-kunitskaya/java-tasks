package com.kunitskaya.domain.collectordomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class A {
    String name;
    int number;
    boolean isSmth;

    public A(String name, int number, boolean isSmth) {
        this.name = name;
        this.number = number;
        this.isSmth = isSmth;
    }

    public static List<A> getAInstances(int numberOfInstances) {
        List<A> instances = new ArrayList<>();

        for (int i = 0; i <= numberOfInstances; i++) {
            A instance = new A("A #" + i, i, true);

            LOGGER.info("Created instance: " + instance.getName());
            instances.add(instance);
        }
        return instances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSmth() {
        return isSmth;
    }

    public void setSmth(boolean smth) {
        isSmth = smth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return number == a.number &&
                isSmth == a.isSmth &&
                Objects.equals(name, a.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number, isSmth);
    }

    public int addNumber(int addition) {
        int initialNumber = this.getNumber();
        int result = initialNumber + addition;

        String message = "Initial number: %s, added number: %s, result: %s";
        LOGGER.info(String.format(message, initialNumber, addition, result));
        return result;
    }

    public boolean hasEvenNumber() {
        if (this.number % 2 == 0) {
            LOGGER.info("Number " + this.number + " is even");
            return true;
        } else {
            LOGGER.info("Number " + this.number + " is not even");
            return false;
        }
    }
}
