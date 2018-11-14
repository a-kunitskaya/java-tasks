package com.kunitskaya.domain.module6.abstractbeans;

public abstract class C {
    private D d;

    protected abstract D createD();

    public D getD() {
        return createD();
    }
}
