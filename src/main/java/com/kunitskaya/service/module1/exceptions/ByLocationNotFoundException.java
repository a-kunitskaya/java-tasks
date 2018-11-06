package com.kunitskaya.service.module1.exceptions;

public class ByLocationNotFoundException extends ApplianceNotFoundException {

    public ByLocationNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public static String getErrorMessage(String value) {
        String parameter = "location";
        return getErrorMessage(parameter, value);
    }
}
