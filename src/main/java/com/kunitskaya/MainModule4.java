package com.kunitskaya;

import com.kunitskaya.domain.module4.Employee;
import com.kunitskaya.domain.module4.Position;
import com.kunitskaya.domain.module4.Salary;
import com.kunitskaya.service.module4.PositionService;
import com.kunitskaya.service.module4.SalaryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainModule4 {

    public static void main(String[] args) {
        //TODO: •	PositionService: delete positions in company

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");


        Salary devSalary = (Salary) context.getBean("dev_salary");
        Position devPosition = (Position) context.getBean("dev_position");
        Employee javaDeveloper = (Employee) context.getBean("java_dev_employee");
        Employee iosDeveloper = (Employee) context.getBean("ios_dev_employee");

        Map<String, Salary> targetPositions = new HashMap<>();
        targetPositions.put("Tester", new Salary(500));
        targetPositions.put("Manager", new Salary(2000));

        List<Position> positions = PositionService.createPositions(targetPositions);

        Map<Position, Salary> updates = new HashMap<>();
        updates.put(devPosition, new Salary(1500));

        PositionService.updatePositions(updates);
        PositionService.readAllPositions();

        PositionService.deletePositions(devPosition);
        PositionService.readAllPositions();

        SalaryService salaryService = new SalaryService(3, 200);
        Salary calculatedSalary = salaryService.calculateSalary(devSalary);


    }

}
