package com.kunitskaya.module6.domain.salaryemulator;

import com.kunitskaya.module4.domain.Salary;
import org.springframework.beans.factory.FactoryBean;

public class SalaryFactoryBean implements FactoryBean<Salary> {
    private double amount;

    @Override
    public Salary getObject() {
        return new Salary(amount);
    }

    @Override
    public Class<?> getObjectType() {
        return Salary.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
