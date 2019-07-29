package by.training.finaltask.parser;

import by.training.finaltask.action.Action;
import by.training.finaltask.entity.Entity;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public static GregorianCalendar parseDate(String format,
                                              String dateStr) throws InvalidFormDataException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new InvalidFormDataException("incorrectDateFormat");
        }
        GregorianCalendar dategreg = new GregorianCalendar();
        dategreg.setTime(date);
        return dategreg;
    }


   public abstract T parse(Action action, List<String> parameters)
           throws InvalidFormDataException, PersistentException;




}
