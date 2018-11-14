package com.kunitskaya.domain.module6.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import static com.kunitskaya.logging.ProjectLogger.*;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    private static int BEANS_COUNT = 0;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("Total initialized beans count: " + BEANS_COUNT);
        LOGGER.info("Step 1: Before initialization of bean: " + beanName);
        BEANS_COUNT++;
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOGGER.info("Step 3: After initialization of bean: " + beanName);
        return bean;
    }
}
