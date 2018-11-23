package com.kunitskaya.module4;

import com.kunitskaya.BaseTest;
import com.kunitskaya.module4.domain.Employee;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EmployeeTest extends BaseTest {

    @Test
    public void nullName() {
        Employee employee = new Employee(null);
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        assertTrue(!violations.isEmpty(), VIOLATIONS_FOUND_MESSAGE);

        String errorMessage = "Name is required";
        violations.stream()
                  .map(ConstraintViolation::getMessage)
                  .forEach(m -> assertEquals(m, errorMessage));
    }
}
