package com.kunitskaya.module6.domain.config;

import com.kunitskaya.module4.domain.Salary;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class SalaryBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Salary) {

            double initialValue = ((Salary) bean).getAmount();
            double finalValue = initialValue + 25;

            String message = "Changing salary in bean post processor from: %s to: %s";
            LOGGER.info(String.format(message, initialValue, finalValue));

            ((Salary) bean).setAmount(finalValue);
        }
        return bean;
    }
}
