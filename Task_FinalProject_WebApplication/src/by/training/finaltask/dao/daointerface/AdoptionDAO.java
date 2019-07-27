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
    int getCountByPetIDandDateNotNull(int petID, GregorianCalendar start,
                                      GregorianCalendar end) throws PersistentException;
    int getCountByPetIDandDateNull(int petID, GregorianCalendar start) throws PersistentException;
    int getAllCount() throws PersistentException;
    boolean delete(Integer petID) throws PersistentException;
}
