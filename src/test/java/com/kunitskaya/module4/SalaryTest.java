package com.kunitskaya.module4;

import com.kunitskaya.BaseTest;
import com.kunitskaya.module4.domain.Salary;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SalaryTest extends BaseTest {
    private static final int INVALID_AMOUNT = 5000;

    @Test
    public void invalidSalary() {
        Salary salary = new Salary(INVALID_AMOUNT);
        Set<ConstraintViolation<Salary>> violations = validator.validate(salary);

        assertTrue(!violations.isEmpty(), VIOLATIONS_FOUND_MESSAGE);

        String errorMessage = "Salary cannot be > 4000";
        violations.stream()
                  .map(ConstraintViolation::getMessage)
                  .forEach(m -> assertEquals(m, errorMessage));
    }
}


