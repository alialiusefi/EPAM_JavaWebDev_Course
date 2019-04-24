package by.training.finaltask.dao.daointerface;

import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.entity.Adoption;

import java.util.List;

public interface AdoptionDAO extends DAO<AdoptionDAO>{

    Adoption get(Integer petID) throws PersistentException;
    List<Adoption> getAll() throws PersistentException;
    boolean delete(Integer petID) throws PersistentException;
}
