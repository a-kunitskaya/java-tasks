package com.kunitskaya.module1.service.exceptions;

public final class ByColorApplianceNotFoundException extends ApplianceNotFoundException {
    public ByColorApplianceNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public static String getErrorMessage(String value) {
        String parameter = "color";
        return getErrorMessage(parameter, value);
    }
}
