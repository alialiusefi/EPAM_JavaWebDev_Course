package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.Shelter;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface ShelterDAO extends DAO<Shelter>{

    List<Shelter> getAll() throws PersistentException;

    Shelter getByID(Integer id) throws PersistentException;

}
