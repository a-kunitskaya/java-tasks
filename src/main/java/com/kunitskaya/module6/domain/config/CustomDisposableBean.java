package com.kunitskaya.module6.domain.config;

import org.springframework.beans.factory.DisposableBean;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class CustomDisposableBean implements DisposableBean {
    @Override
    public void destroy() {
        LOGGER.info("Destroying context...");
    }
}
