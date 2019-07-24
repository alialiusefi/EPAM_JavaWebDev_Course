package by.training.finaltask.parser;

import by.training.finaltask.action.Action;
import by.training.finaltask.entity.Entity;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public abstract class FormParser<T> {

    private static final String NUMBER_REGEX = "[1-9]+";

    public static int parsePageNumber(String pageParameter, int amountOfPages) {
        if (pageParameter.matches(NUMBER_REGEX)) {
            int pageNumber = Integer.parseInt(
                    pageParameter);
            if (pageNumber <= amountOfPages) {
                return pageNumber;
            } else {
                return 1;
            }
        }
        return 1;
    }

   public abstract T parse(Action action, List<String> parameters)
           throws InvalidFormDataException, PersistentException;




}
