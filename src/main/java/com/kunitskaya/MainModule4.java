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
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

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

        StandardEvaluationContext context = new StandardEvaluationContext(PositionService.class);
        Position positionToDelete = parser.parseExpression("positions[0]").getValue(context, Position.class);

        positionService.deletePositions(positionToDelete);
        positionService.readAllPositions();

        salaryService.calculateSalary(salary);
    }


}
