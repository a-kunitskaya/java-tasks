package com.kunitskaya.module6.domain.abstractbeans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class F {

    public void init() {
        LOGGER.info("Init bean F");
    }

    public void destroy() {
        LOGGER.info("Destroy bean F");
    }

    @PostConstruct
    public void postConstruct(){
        LOGGER.info("Calling @PostConstruct on bean F");
    }

    @PreDestroy
    public void preDestroy() {
        LOGGER.info("Calling @PreDestroy on bean F");
    }
}
