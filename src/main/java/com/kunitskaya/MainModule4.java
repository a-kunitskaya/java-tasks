package com.kunitskaya;

import com.kunitskaya.module4.domain.Employee;
import com.kunitskaya.module4.domain.Position;
import com.kunitskaya.module4.domain.Salary;
import com.kunitskaya.module4.service.EmployeeService;
import com.kunitskaya.module4.service.PositionService;
import com.kunitskaya.module4.service.SalaryService;
import com.kunitskaya.module4.service.config.AppContext;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kunitskaya.logging.ProjectLogger.*;

public class MainModule4 {
    private static ExpressionParser parser = new SpelExpressionParser();

    public static void main(String[] args) {

        //emulate a few years of company life
        for (int i = 1; i < RandomUtils.nextInt(4, 7); i++) {
            LOGGER.info("Year: #" + i);

            //Task 1: no autowiring
            ApplicationContext appContextNoAutowiring = new ClassPathXmlApplicationContext("module4/Beans.xml");

            Salary devSalary = (Salary) appContextNoAutowiring.getBean("dev_salary");

            Position devPosition = (Position) appContextNoAutowiring.getBean("dev_position");
            Employee javaDeveloper = (Employee) appContextNoAutowiring.getBean("java_dev_employee");
            Employee iosDeveloper = (Employee) appContextNoAutowiring.getBean("ios_dev_employee");

            //autowiring with XML
            ApplicationContext appContextAutowiring = new ClassPathXmlApplicationContext("module4/Beans_autowiring.xml");
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

            LOGGER.info("Finished year: #" + i);
        }
    }
}
