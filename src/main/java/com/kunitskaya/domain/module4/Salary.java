package com.kunitskaya.domain.module4;

import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class Salary {

    @Value("0.0")
    private double amount;

    public Salary(double amount) {
        this.amount = amount;
    }

    public Salary() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return Double.compare(salary.amount, amount) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Salary{" +
                "amount=" + amount +
                '}';
    }
}
