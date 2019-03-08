package by.training.task2.validator;

import by.training.task2.exception.IncorrectDataException;

import java.util.List;

public class DataValidator {

    private final String INT_REGEX = "^\\d+$";

    public List<String> validateData(List<String> list) throws IncorrectDataException {
        if (list.size() != 4) {
            throw new IncorrectDataException("Incorrect Data Structure!");
        }
        for (String str : list) {
            if (!str.matches(INT_REGEX)) {
                throw new IncorrectDataException("Incorrect Data Type!");
            }
        }
        return list;
    }

}
