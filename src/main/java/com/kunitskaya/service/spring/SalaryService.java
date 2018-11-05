package com.kunitskaya.service.spring;

import com.kunitskaya.domain.beans.Salary;
import com.kunitskaya.logging.ProjectLogger;
import sun.management.resources.agent;

import static com.kunitskaya.logging.ProjectLogger.*;

public class SalaryService {
    private double inflation;
    private double exchangeRate;

    public SalaryService(double inflation, double exchangeRate) {
        this.inflation = inflation;
        this.exchangeRate = exchangeRate;
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Salary calculateSalary(Salary salary){
        LOGGER.info("Initial salary in $ : " + salary.getAmount());

        double amount = salary.getAmount();
        amount *= inflation * exchangeRate;
        salary.setAmount(amount);

        LOGGER.info("Calculated salary: " + amount);
        return salary;
    }
}
