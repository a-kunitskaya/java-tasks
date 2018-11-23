package com.kunitskaya.module6.domain.abstractbeans;

public abstract class C {
    private D d;

    protected abstract D createD();

    public D getD() {
        return createD();
    }
}
