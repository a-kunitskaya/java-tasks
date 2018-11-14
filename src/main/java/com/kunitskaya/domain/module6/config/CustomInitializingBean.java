package com.kunitskaya.domain.module6.config;

import com.kunitskaya.logging.ProjectLogger;
import org.springframework.beans.factory.InitializingBean;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class CustomInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info(" Step 2: after context properties are set");
    }
}
