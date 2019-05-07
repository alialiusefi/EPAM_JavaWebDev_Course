package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.Pet;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface PetDAO extends DAO<Pet> {

    Pet get(Integer ID) throws PersistentException;
    List<Pet> getAll() throws PersistentException;
    boolean delete(Integer ID) throws PersistentException;



}
