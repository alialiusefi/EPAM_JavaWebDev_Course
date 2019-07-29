package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.PersistentException;

import java.awt.print.PrinterAbortException;
import java.util.GregorianCalendar;
import java.util.List;

public interface AdoptionDAO extends DAO<Adoption>{

    Adoption get(Integer petID) throws PersistentException;
    List<Adoption> getAll(int offset, int rowcount) throws PersistentException;
    List<Adoption> getAll(Integer petID, int offset, int rowcount) throws PersistentException;
    List<Adoption> getAllBetweenDates(GregorianCalendar start, GregorianCalendar end,
                                      int offset, int rowcount) throws PersistentException;
    List<Adoption> getAllBetweenDatesCurrentUser(int userID, GregorianCalendar start,
                                                 GregorianCalendar end, int offset, int rowcount)
            throws PersistentException;
    List<Adoption> getAllPetName(String petName,int offset, int rowcount)
            throws PersistentException;
    List<Adoption> getAllPetNameCurrentUser(int userID, String petName,int offset, int rowcount)
            throws PersistentException;
    int getCountPetName(String petName) throws PersistentException;

    int getCountPetNameCurrentUser(String petName, int userID) throws PersistentException;

    int getCountBetweenDatesCurrentUser(int userID, GregorianCalendar start,
                                        GregorianCalendar end) throws PersistentException;
    int getCountBetweenDates(GregorianCalendar start, GregorianCalendar end)
            throws PersistentException;
    int getCountByPetIDandDateNotNull(int petID, GregorianCalendar start,
                                      GregorianCalendar end) throws PersistentException;
    int getCountByPetIDandDateNull(int petID, GregorianCalendar start) throws PersistentException;
    int getAllCount() throws PersistentException;
    boolean delete(Integer petID) throws PersistentException;
}
