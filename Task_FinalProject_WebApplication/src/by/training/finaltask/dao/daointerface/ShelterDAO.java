package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.Breed;
import by.training.finaltask.entity.Shelter;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface ShelterDAO extends DAO<Shelter>{

    List<Breed> getAll() throws PersistentException;

    Breed getByID(Integer id) throws PersistentException;

}
