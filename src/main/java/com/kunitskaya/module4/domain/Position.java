package com.kunitskaya.module4.domain;

import javax.validation.constraints.Pattern;
import java.util.Objects;

import static com.kunitskaya.module4.service.PositionService.positions;

public class Position {

    @Pattern(regexp = "[A-Z, a-z]*", message = "Position name should consist of letters only")
    private String name;
    private Salary salary;

    public Position(String name, Salary salary) {
        this.name = name;
        this.salary = salary;
        positions.add(this);
    }

    public Position(String name) {
        this.name = name;
        positions.add(this);
    }

    public Position() {
        positions.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Salary getSalary() {
        if (salary == null) {
            salary = new Salary();
        }
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(name, position.name) &&
                Objects.equals(salary, position.salary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, salary);
    }

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
