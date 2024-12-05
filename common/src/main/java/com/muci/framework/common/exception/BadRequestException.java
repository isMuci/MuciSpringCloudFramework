package com.muci.framework.common.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Bad Request");
    }

    public BadRequestException(String message) {
        super(message);
    }
}