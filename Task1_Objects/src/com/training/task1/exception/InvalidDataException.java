package com.training.task1.exception;

public final class InvalidDataException extends Exception {

    public InvalidDataException(final String message) {
        super(message);
    }

    public InvalidDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
