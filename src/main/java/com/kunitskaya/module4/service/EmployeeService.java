package com.kunitskaya.module4.service;

import com.kunitskaya.module4.domain.Employee;
import com.kunitskaya.module4.domain.Position;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class EmployeeService {
    private Employee employee;

    public EmployeeService(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void hire(Position position) {
        String message = "Hiring employee: %s as: %s";
        LOGGER.info(String.format(message, employee.getName(), position.getName()));
        employee.setPosition(position);
    }

    public void fire() {
        String message = "Firing employee: %s source position: %s";
        LOGGER.info(String.format(message, employee.getName(), employee.getPosition().getName()));
        employee.setPosition(null);
    }

    @Override
    public String toString() {
        return "EmployeeService{" +
                "employee=" + employee +
                '}';
    }
}
