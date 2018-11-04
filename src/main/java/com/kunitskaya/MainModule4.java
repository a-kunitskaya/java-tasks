package com.kunitskaya;

import com.kunitskaya.domain.beans.Employee;
import com.kunitskaya.domain.beans.Position;
import com.kunitskaya.domain.beans.Salary;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainModule4 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Salary devSalary = (Salary)context.getBean("dev_salary");
        System.out.println(devSalary);

        Position devPosition = (Position)context.getBean("dev_position");
        System.out.println(devPosition);

        Employee javaDeveloper = (Employee) context.getBean("java_dev_employee");
        System.out.println(javaDeveloper.toString());

        Employee iosDeveloper = (Employee) context.getBean("ios_dev_employee");
        System.out.println(iosDeveloper);
    }

}
