package com.kunitskaya.service.spring;

import com.kunitskaya.domain.beans.Employee;
import com.kunitskaya.domain.beans.Position;

public class EmployeeService {

    public static void hire(Employee employee, Position position) {
        employee.setPosition(position);
    }

    public static void fire(Employee employee) {
        employee.setPosition(null);
    }
}
