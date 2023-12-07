package com.usenko.ktest.model.exceptions;

public class ForbiddenOperationException extends RuntimeException{
    public ForbiddenOperationException(String message) {
        super(message);
    }
}
