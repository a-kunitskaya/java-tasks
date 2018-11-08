package com.kunitskaya.domain.module4;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import java.util.Objects;

public class Salary {

    @Max(value = 4000, message = "Salary cannot be > 4000")
    @Value("0.0")
    private double amount;

    public Salary(double amount) {
        this.amount = amount;
    }

    public Salary() {
    }

    @Max(value = 4000, message = "Salary cannot be > 4000")
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
