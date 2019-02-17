package com.training.task1.exception;

public final class InvalidDataInputException extends Exception {

    public InvalidDataInputException(final String message) {
        super(message);
    }

    public InvalidDataInputException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
