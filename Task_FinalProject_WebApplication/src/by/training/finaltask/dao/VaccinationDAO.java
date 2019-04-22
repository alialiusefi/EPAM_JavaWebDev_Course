package by.training.finaltask.dao;

import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.entity.Vaccination;

import java.util.List;

public interface VaccinationDAO extends DAO<Vaccination>{

    Vaccination get(Integer vaccID) throws PersistentException;
    List<Vaccination> getAll() throws PersistentException;
    boolean delete(Integer vaccID) throws PersistentException;
}
