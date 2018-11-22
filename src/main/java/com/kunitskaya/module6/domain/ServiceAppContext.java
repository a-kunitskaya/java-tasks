package com.kunitskaya.module6.domain;

import com.kunitskaya.module4.domain.Employee;
import com.kunitskaya.module4.domain.Salary;
import com.kunitskaya.module4.service.EmployeeService;
import com.kunitskaya.module4.service.PositionService;
import com.kunitskaya.module4.service.SalaryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceAppContext {

  @Bean
  public PositionService positionService() {
    return new PositionService();
  }

  @Bean
  public SalaryService salaryService() {
    return new SalaryService(new Salary());
  }

  @Bean
  public EmployeeService employeeService() {
    return new EmployeeService(new Employee());
  }
  }
