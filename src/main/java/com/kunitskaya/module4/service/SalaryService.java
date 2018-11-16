package com.kunitskaya.module4.service;

import com.kunitskaya.module4.domain.Position;
import com.kunitskaya.module4.domain.Salary;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.text.DecimalFormat;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class SalaryService {
    public static final String AMOUNT_PATTERN = "#.##";
    private double inflation;
    private double exchangeRate;
    private Salary salary;

    public SalaryService(Salary salary) {
        this.salary = salary;
    }

    public static double getFormattedValue(double value, String pattern) {
        ConversionService service = new DefaultFormattingConversionService();
        DecimalFormat formatter = new DecimalFormat(pattern);
        String formattedValue = formatter.format(value);
        return service.convert(formattedValue, Double.class);
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

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Salary calculateSalary(Salary salary) {
        LOGGER.info("Initial salary in $: " + salary.getAmount());

        double amount = salary.getAmount();
        amount = amount * (1 + inflation) * exchangeRate;

        double realAmount = getFormattedValue(amount, AMOUNT_PATTERN);
        salary.setAmount(realAmount);

        LOGGER.info("Calculated salary: " + realAmount);
        return salary;
    }

    @Override
    public String toString() {
        return "SalaryService{" +
                "inflation=" + inflation +
                ", exchangeRate=" + exchangeRate +
                ", salary=" + salary +
                '}';
    }
}
