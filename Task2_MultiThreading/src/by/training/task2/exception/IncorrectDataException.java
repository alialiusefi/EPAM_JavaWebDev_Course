package by.training.task2.exception;

public class IncorrectDataException extends Exception {

    public IncorrectDataException(final String message) {
        super(message);
    }

    public IncorrectDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

}


