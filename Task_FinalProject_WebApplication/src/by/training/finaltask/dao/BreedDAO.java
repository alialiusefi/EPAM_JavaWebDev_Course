package by.training.finaltask.dao;

import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.entity.Breed;

import java.util.List;

public interface BreedDAO extends DAO<Breed> {

    Breed get(String name) throws PersistentException;
    List<Breed> getAll() throws PersistentException;
    boolean delete(String name) throws PersistentException;

}
