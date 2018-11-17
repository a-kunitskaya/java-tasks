package com.kunitskaya.module4.service.config;

import com.kunitskaya.module4.domain.Employee;
import com.kunitskaya.module4.domain.Position;
import com.kunitskaya.module4.domain.Salary;
import com.kunitskaya.module4.service.EmployeeService;
import com.kunitskaya.module4.service.PositionService;
import com.kunitskaya.module4.service.SalaryService;
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

    @Bean
    public PositionService positionService() {
        return new PositionService();
    }

    @Bean
    public SalaryService salaryService() {
        return new SalaryService(salary());
    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService(employee());
    }


}
