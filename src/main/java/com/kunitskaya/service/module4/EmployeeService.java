package com.kunitskaya.service.module4;

import com.kunitskaya.domain.module4.Employee;
import com.kunitskaya.domain.module4.Position;

public class EmployeeService {

    public static void hire(Employee employee, Position position) {
        employee.setPosition(position);
    }

    public static void fire(Employee employee) {
        employee.setPosition(null);
    }
}
