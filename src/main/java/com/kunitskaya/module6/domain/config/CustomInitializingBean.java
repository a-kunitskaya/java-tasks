package com.kunitskaya.module6.domain.config;

import org.springframework.beans.factory.InitializingBean;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class CustomInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Context properties are set");
    }
}
