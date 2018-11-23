package com.kunitskaya.module6.domain.config.handlers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ContextStartedEventHandler implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        LOGGER.info("ContextStartedEvent happened");
    }
}
