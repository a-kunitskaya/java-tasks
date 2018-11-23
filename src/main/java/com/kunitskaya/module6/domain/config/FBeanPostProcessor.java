package com.kunitskaya.module6.domain.config;

import com.kunitskaya.module6.domain.abstractbeans.F;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class FBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof F) {
            LOGGER.info("Before initialization of F bean");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof F) {
            LOGGER.info("After bean F is initialized");
        }
        return bean;
    }
}
