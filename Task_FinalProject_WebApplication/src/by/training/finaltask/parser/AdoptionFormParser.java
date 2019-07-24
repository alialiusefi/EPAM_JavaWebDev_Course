package by.training.finaltask.parser;

import by.training.finaltask.action.Action;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public class AdoptionFormParser extends FormParser<Adoption> {

    @Override
    public Adoption parse(Action action, List<String> parameters) throws InvalidFormDataException, PersistentException {
        return null;
    }
}
