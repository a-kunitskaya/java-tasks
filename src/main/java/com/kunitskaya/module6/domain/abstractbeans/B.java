package com.kunitskaya.module6.domain.abstractbeans;

public class B {
    private String name;
    private A a;

    public B(A a) {
        this.a = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "B{" +
                "name='" + name + '\'' +
                ", a=" + a +
                '}';
    }
}
