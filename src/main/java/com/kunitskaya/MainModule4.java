package com.kunitskaya;

import com.kunitskaya.domain.module4.Employee;
import com.kunitskaya.domain.module4.Position;
import com.kunitskaya.domain.module4.Salary;
import com.kunitskaya.service.module4.EmployeeService;
import com.kunitskaya.service.module4.PositionService;
import com.kunitskaya.service.module4.SalaryService;
import com.kunitskaya.service.module4.config.AppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainModule4 {

    public static void main(String[] args) {
        //TODO: •	PositionService: delete positions in company

        //no autowiring
        ApplicationContext appContextNoAutowiring = new ClassPathXmlApplicationContext("Beans.xml");

        Salary devSalary = (Salary) appContextNoAutowiring.getBean("dev_salary");
        Position devPosition = (Position) appContextNoAutowiring.getBean("dev_position");
        Employee javaDeveloper = (Employee) appContextNoAutowiring.getBean("java_dev_employee");
        Employee iosDeveloper = (Employee) appContextNoAutowiring.getBean("ios_dev_employee");


        //autowiring
        ApplicationContext appContextAnnotations = new AnnotationConfigApplicationContext(AppContext.class);
        PositionService positionService = appContextAnnotations.getBean(PositionService.class);

        ApplicationContext appContextAutowiring = new ClassPathXmlApplicationContext("Beans_autowiring.xml");

        EmployeeService employeeService = (EmployeeService) appContextAutowiring.getBean("employee_service");
        SalaryService salaryService = (SalaryService) appContextAutowiring.getBean("salary_service");
    }


}
