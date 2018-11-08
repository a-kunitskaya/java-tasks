package com.kunitskaya;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BaseTest {
    protected static final String VIOLATIONS_FOUND_MESSAGE = "Violations are found:";

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    protected Validator validator = factory.getValidator();

}
