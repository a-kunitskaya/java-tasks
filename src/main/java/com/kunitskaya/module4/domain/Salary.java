package com.kunitskaya.module4.domain;

import com.kunitskaya.module4.service.SalaryService;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import java.util.Objects;

import static com.kunitskaya.module4.service.SalaryService.AMOUNT_PATTERN;

public class Salary {
    @Max(value = 4000, message = "Salary cannot be > 4000")
    @Value("0.0")
    private double amount;

    public Salary(double amount) {
        this.amount = SalaryService.getFormattedValue(amount, AMOUNT_PATTERN);
    }

    public Salary() {
    }

    public static Salary createInstance(){
        return new Salary();
    }

    @Max(value = 4000, message = "Salary cannot be > 4000")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = SalaryService.getFormattedValue(amount, AMOUNT_PATTERN);
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
