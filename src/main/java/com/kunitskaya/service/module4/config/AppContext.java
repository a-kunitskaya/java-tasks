package com.kunitskaya.service.module4.config;

import com.kunitskaya.domain.module4.Employee;
import com.kunitskaya.domain.module4.Position;
import com.kunitskaya.domain.module4.Salary;
import com.kunitskaya.service.module4.EmployeeService;
import com.kunitskaya.service.module4.PositionService;
import com.kunitskaya.service.module4.SalaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
