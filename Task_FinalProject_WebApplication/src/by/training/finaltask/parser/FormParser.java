package by.training.finaltask.parser;

import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface FormParser<T> {

    T parse(List<String> parameters) throws InvalidFormDataException, PersistentException;


}
