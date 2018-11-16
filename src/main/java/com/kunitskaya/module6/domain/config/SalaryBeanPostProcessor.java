package com.kunitskaya.module6.domain.config;

import com.kunitskaya.logging.ProjectLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class SalaryBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//       String message = "Mutating salary from: %s to: ";
//
//        LOGGER.info(String.format());
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
