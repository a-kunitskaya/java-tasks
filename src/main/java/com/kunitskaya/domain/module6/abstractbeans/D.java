package com.kunitskaya.domain.module6.abstractbeans;

public class D {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "D{" +
                "name='" + name + '\'' +
                '}';
    }
}
