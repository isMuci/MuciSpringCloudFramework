package com.muci.framework.common.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Required Resource Not Found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}