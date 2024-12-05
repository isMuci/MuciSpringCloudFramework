package com.muci.framework.common.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Unauthorized Device Access");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}