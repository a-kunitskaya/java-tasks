package com.kunitskaya.domain.module6.config;

import com.kunitskaya.logging.ProjectLogger;
import org.springframework.beans.factory.DisposableBean;

import static com.kunitskaya.logging.ProjectLogger.*;

public class CustomDisposableBean implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        LOGGER.info("Step 4: destroy context");
    }
}
