package com.kunitskaya.module4.service.config;

import com.kunitskaya.module4.domain.Employee;
import com.kunitskaya.module4.domain.Position;
import com.kunitskaya.module4.domain.Salary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppContext {
    @Bean
    public Salary salary() {
        return new Salary();
    }

    @Bean
    public Position position() {
        return new Position();
    }

    @Bean
    @Scope("prototype")
    public Employee employee() {
        return new Employee();
    }
}
