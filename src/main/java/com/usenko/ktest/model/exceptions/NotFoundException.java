package com.usenko.ktest.model.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message, Long id) {
        super(message + " with id=" + id + " was not found");
    }
}