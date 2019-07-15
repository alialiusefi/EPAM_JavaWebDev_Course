package by.training.finaltask.validator;

import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface FormValidator<T> {

    T validate(List<String> parameters) throws InvalidFormDataException, PersistentException;

}
