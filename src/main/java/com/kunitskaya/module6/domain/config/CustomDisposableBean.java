package com.kunitskaya.module6.domain.config;

import org.springframework.beans.factory.DisposableBean;

import static com.kunitskaya.logging.ProjectLogger.*;

public class CustomDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        LOGGER.info("Destroying context...");
    }
}
