package by.training.finaltask.parser;

import by.training.finaltask.action.Action;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.InvalidFormDataException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AdoptionFormParser extends FormParser<Adoption> {

    private static final int PETID = 0;
    private static final int ADOPTIONSTART = 1;
    private static final int ADOPTIONEND = 2;
    private static final int USERID = 3;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public Adoption parse(Action action, List<String> parameters)
            throws InvalidFormDataException {
        if (!parameters.isEmpty() && !parameters.contains(null) && !parameters.contains("")) {
            int petID = Integer.parseInt(parameters.get(PETID));
            GregorianCalendar adoptionStartCalendar = validateDate(parameters.get(ADOPTIONSTART));
            GregorianCalendar adoptionEndCalendar;
            if (parameters.get(ADOPTIONEND).equals("INDEFINITE")) {
                adoptionEndCalendar = null;
            } else {
                adoptionEndCalendar = validateDate(parameters.get(ADOPTIONEND));
            }
            int userID = Integer.parseInt(parameters.get(USERID));
            return new Adoption(petID, adoptionStartCalendar, adoptionEndCalendar, userID);
        }
        throw new InvalidFormDataException("fillAllFields");
    }

    private GregorianCalendar validateDate(String dateStr) throws InvalidFormDataException {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new InvalidFormDataException("incorrectDateFormat");
        }
        GregorianCalendar dategreg = new GregorianCalendar();
        dategreg.setTime(date);
        Calendar current = GregorianCalendar.getInstance();
        if (dategreg.compareTo(current) > 0) {
            throw new InvalidFormDataException("incorrectDateFormat");
        }
        return dategreg;
    }
}
