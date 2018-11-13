package com.kunitskaya.domain.module6.abstractbeans;

public class C {
    private String name;
    private D d;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "C{" +
                "name='" + name + '\'' +
                ", d=" + d +
                '}';
    }
}
