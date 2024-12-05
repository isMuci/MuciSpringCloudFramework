package com.muci.framework.common.exception;

public class RepeatPasswordException extends RuntimeException {

    public RepeatPasswordException() {
        super("Repeat Password When Update");
    }

    public RepeatPasswordException(String message) {
        super(message);
    }
}