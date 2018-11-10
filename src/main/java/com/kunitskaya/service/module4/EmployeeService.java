package com.kunitskaya.service.module4;

import com.kunitskaya.domain.module4.Employee;
import com.kunitskaya.domain.module4.Position;
import com.kunitskaya.logging.ProjectLogger;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class EmployeeService {
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private Employee employee;

    public EmployeeService(Employee employee) {
        this.employee = employee;
    }

    public void hire(Position position) {
        String message = "Hiring employee: %s as: %s";
        LOGGER.info(String.format(message, employee.getName(), position.getName()));
        employee.setPosition(position);
    }

    public void fire() {
        String message = "Firing employee: %s from position: %s";
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
