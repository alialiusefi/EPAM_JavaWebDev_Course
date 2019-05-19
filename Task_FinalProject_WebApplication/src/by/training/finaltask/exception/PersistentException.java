package by.training.finaltask.exception;

public class PersistentException extends Exception {

    public PersistentException() { }

    public PersistentException(Throwable cause)
    {
        super(cause);
    }

    public PersistentException(String message) {
        super(message);
    }

    public PersistentException(String message, Throwable cause) {
        super(message, cause);
    }
}
