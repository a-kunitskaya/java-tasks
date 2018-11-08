package com.kunitskaya.domain.module4;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Employee {

    @NotNull(message = "Name is required")
    private String name;
    private boolean isStudent;
    private Position position;
    private Salary salary;

    public Employee(String name, boolean isStudent, Position position, Salary salary) {
        this.name = name;
        this.isStudent = isStudent;
        this.position = position;
        this.salary = salary;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return isStudent == employee.isStudent &&
                Objects.equals(name, employee.name) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, isStudent, position, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", isStudent=" + isStudent +
                ", position=" + position +
                ", salary=" + salary +
                '}';
    }
}
