package com.kunitskaya.exceptions;

public final class ApplianceNotFoundException extends CustomException {

    public ApplianceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
