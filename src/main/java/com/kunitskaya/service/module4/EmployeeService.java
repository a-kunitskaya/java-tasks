package com.kunitskaya.service.module4;

import com.kunitskaya.domain.module4.Employee;
import com.kunitskaya.domain.module4.Position;

public class EmployeeService {
    private Employee employee;

    public EmployeeService(Employee employee) {
        this.employee = employee;
    }

    public void hire(Position position) {
        employee.setPosition(position);
    }

    public void fire() {
        employee.setPosition(null);
    }

    @Override
    public String toString() {
        return "EmployeeService{" +
                "employee=" + employee +
                '}';
    }
}
