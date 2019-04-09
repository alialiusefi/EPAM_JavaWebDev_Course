package by.training.task4.exception;

public class EnumConstantNotFoundException extends Exception {

    public EnumConstantNotFoundException() {
    }

    public EnumConstantNotFoundException(String message) {
        super(message);
    }

    public EnumConstantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
