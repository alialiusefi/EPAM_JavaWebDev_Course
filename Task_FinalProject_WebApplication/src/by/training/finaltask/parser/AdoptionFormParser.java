package by.training.finaltask.parser;

import by.training.finaltask.action.Action;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.InvalidFormDataException;

import java.util.GregorianCalendar;
import java.util.List;

public class AdoptionFormParser extends FormParser<Adoption> {

    private static final int ADOPTIONID = 0;
    private static final int PETID = 1;
    private static final int ADOPTIONSTART = 2;
    private static final int ADOPTIONEND = 3;
    private static final int USERID = 4;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public Adoption parse(Action action, List<String> parameters)
            throws InvalidFormDataException {
        if (!parameters.isEmpty() && !parameters.contains(null) && !parameters.contains("")) {
            int adoptionID = Integer.parseInt(parameters.get(ADOPTIONID));
            int petID = Integer.parseInt(parameters.get(PETID));
            GregorianCalendar adoptionStartCalendar = parseDate(DATE_FORMAT,
                    parameters.get(ADOPTIONSTART));
            GregorianCalendar adoptionEndCalendar;
            if (parameters.get(ADOPTIONEND).equals("INDEFINITE")) {
                adoptionEndCalendar = null;
            } else {
                adoptionEndCalendar = parseDate(DATE_FORMAT,
                        parameters.get(ADOPTIONEND));
            }
            int userID = Integer.parseInt(parameters.get(USERID));
            return new Adoption(adoptionID,petID, adoptionStartCalendar, adoptionEndCalendar, userID);
        }
        throw new InvalidFormDataException("fillAllFields");
    }

}
