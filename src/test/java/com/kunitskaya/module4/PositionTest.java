package com.kunitskaya.module4;

import com.kunitskaya.BaseTest;
import com.kunitskaya.module4.domain.Position;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PositionTest extends BaseTest {
    private static final String INVALID_POSITION_NAME = "Dev_1";

    @Test
    public void invalidPosition() {
        Position position = new Position(INVALID_POSITION_NAME);
        Set<ConstraintViolation<Position>> violations = validator.validate(position);

        assertTrue(!violations.isEmpty(), VIOLATIONS_FOUND_MESSAGE);

        String errorMessage = "Position name should consist of letters only";
        violations.stream()
                  .map(ConstraintViolation::getMessage)
                  .forEach(m -> assertEquals(m, errorMessage));
    }
}
