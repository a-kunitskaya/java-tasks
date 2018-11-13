package com.kunitskaya.domain.module6.abstractbeans;

import com.kunitskaya.logging.ProjectLogger;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class E {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void replaceMethod(){
        LOGGER.info("Method has not been replaced yet");
    }

    @Override
    public String toString() {
        return "E{" +
                "name='" + name + '\'' +
                '}';
    }
}
