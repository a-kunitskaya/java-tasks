package com.kunitskaya.exceptions;

/**
 * Custom final unchecked exception
 */
public final class ApplianceNotFoundException extends CustomUncheckedException {

    public ApplianceNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
