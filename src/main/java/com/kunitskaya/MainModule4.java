package com.kunitskaya;

import com.kunitskaya.domain.module4.Employee;
import com.kunitskaya.domain.module4.Position;
import com.kunitskaya.domain.module4.Salary;
import com.kunitskaya.service.module4.EmployeeService;
import com.kunitskaya.service.module4.PositionService;
import com.kunitskaya.service.module4.SalaryService;
import com.kunitskaya.service.module4.config.AppContext;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainModule4 {
    private static ExpressionParser parser = new SpelExpressionParser();

    public static void main(String[] args) {
        //Task 1: no autowiring
        ApplicationContext appContextNoAutowiring = new ClassPathXmlApplicationContext("Beans.xml");

        Salary devSalary = (Salary) appContextNoAutowiring.getBean("dev_salary");

        Position devPosition = (Position) appContextNoAutowiring.getBean("dev_position");
        Employee javaDeveloper = (Employee) appContextNoAutowiring.getBean("java_dev_employee");
        Employee iosDeveloper = (Employee) appContextNoAutowiring.getBean("ios_dev_employee");

        //autowiring with XML
        ApplicationContext appContextAutowiring = new ClassPathXmlApplicationContext("Beans_autowiring.xml");
        EmployeeService employeeServiceXml = (EmployeeService) appContextAutowiring.getBean("employee_service");
        SalaryService salaryServiceXml = (SalaryService) appContextAutowiring.getBean("salary_service");


        //Task 2: autowiring with annotations
        ApplicationContext appContextAnnotations = new AnnotationConfigApplicationContext(AppContext.class);

        Salary salary = appContextAnnotations.getBean(Salary.class);
        Position position = appContextAnnotations.getBean(Position.class);
        Employee employee = appContextAnnotations.getBean(Employee.class);


        SalaryService salaryService = appContextAnnotations.getBean(SalaryService.class);
        PositionService positionService = appContextAnnotations.getBean(PositionService.class);
        EmployeeService employeeService = appContextAnnotations.getBean(EmployeeService.class);

        //Use methods
        positionService.readAllPositions();

        Map<String, Double> positionsToCreate = new HashMap<>();
        positionsToCreate.put("tester", 1000.0);
        positionsToCreate.put("manager", 3000.0);
        List<Position> createdPositions = positionService.createPositions(positionsToCreate);
        positionService.readAllPositions();

        Map<Position, Double> positionsToUpdate = new HashMap<>();
        createdPositions.forEach(p ->
                positionsToUpdate.put(p, RandomUtils.nextDouble(1500.0, 3500.0))
        );
        positionService.updatePositions(positionsToUpdate);
        positionService.readAllPositions();

        StandardEvaluationContext context = new StandardEvaluationContext(PositionService.class);
        Position positionToDelete = parser.parseExpression("positions[0]").getValue(context, Position.class);
        positionService.deletePositions(positionToDelete);
        positionService.readAllPositions();

        employeeService.setEmployee(iosDeveloper);
        employeeService.hire(devPosition);
        employeeService.fire();


        salaryService.setExchangeRate(2);
        salaryService.setInflation(0.3);
        salaryService.calculateSalary(devSalary);

    }


}
