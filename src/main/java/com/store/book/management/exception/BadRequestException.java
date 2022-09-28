package com.store.book.management.exception;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 101L;

    public BadRequestException(String message) {
        super(message);
    }
}
