package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;

import java.util.GregorianCalendar;
import java.util.List;

public interface AdoptionService extends Service {

    Adoption get(int ID) throws PersistentException;

    List<Adoption> getAll(int offset, int rowcount) throws PersistentException;

    List<Adoption> getAllBetweenDates(GregorianCalendar start, GregorianCalendar end, int offset,
                                      int rowcount) throws PersistentException;

    List<Adoption> getAllPetName(String petName, int offset, int rowcount)
            throws PersistentException;

    int getCountPetName(String petName) throws PersistentException;

    List<Adoption> getAllPetNameCurrentUser(int userID, String petName, int offset, int rowcount)
            throws PersistentException;

    int getCountPetNameCurrentUser(int userID, String petName)
            throws PersistentException;

    int getCountBetweenDates(GregorianCalendar start, GregorianCalendar end)
    throws PersistentException;

    List<Adoption> getAllBetweenDatesCurrentUser(int userID, GregorianCalendar start,
                                                 GregorianCalendar end, int offset, int rowcount)
            throws PersistentException;

    int getCountBetweenDatesCurrentUser(int userID, GregorianCalendar start,
                                        GregorianCalendar end) throws PersistentException;

    int getAllCount() throws PersistentException;

    Integer add(Adoption adoption) throws PersistentException, InvalidFormDataException;

    void update(Adoption adoption) throws PersistentException, InvalidFormDataException;

    void delete(int adoptionID) throws PersistentException, InvalidFormDataException;

}
