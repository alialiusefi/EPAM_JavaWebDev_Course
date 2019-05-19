package by.training.finaltask.validator;

import by.training.finaltask.exception.InvalidFormDataException;

import java.util.List;

public interface FormValidator {

    Object validate(List<String> parameters) throws InvalidFormDataException;

}
