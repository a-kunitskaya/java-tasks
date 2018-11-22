package com.kunitskaya.module6.domain;

import com.kunitskaya.module4.domain.Salary;
import com.kunitskaya.module6.domain.config.handlers.ContextClosedEventHandler;
import com.kunitskaya.module6.domain.config.handlers.ContextStartedEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppListenerContext {

    @Bean
    public Salary salary() {
        return new Salary();
    }

    @Bean
    public ContextStartedEventHandler contextStartedEventHandler(){
        return new ContextStartedEventHandler();
    }

    @Bean
    public ContextClosedEventHandler contextClosedEventHandler(){
        return new ContextClosedEventHandler();
    }
}
