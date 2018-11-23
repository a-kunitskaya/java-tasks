package com.kunitskaya.module6.domain.config.handlers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class ContextClosedEventHandler implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        LOGGER.info("ContextClosedEvent happened");
    }
}
