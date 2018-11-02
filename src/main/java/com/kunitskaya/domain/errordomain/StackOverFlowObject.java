package com.kunitskaya.domain.errordomain;

public class StackOverFlowObject {

    //constructor instantiating this
    public StackOverFlowObject() {
        new StackOverFlowObject();
    }
}
