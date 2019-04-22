package by.training.finaltask.dao;

import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.entity.Shelter;

import java.util.List;

public interface ShelterDAO extends DAO<Shelter>{
    Shelter get(Integer ID) throws PersistentException;
    List<Shelter> getAll() throws PersistentException;
    boolean delete(Integer ID) throws PersistentException;
}
