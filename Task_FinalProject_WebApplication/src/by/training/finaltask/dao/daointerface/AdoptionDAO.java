package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface AdoptionDAO extends DAO<Adoption>{

    by.training.finaltask.entity.Adoption get(Integer petID) throws PersistentException;
    List<Adoption> getAll() throws PersistentException;
    List<Adoption> getAllByID(Integer petID) throws PersistentException;
    boolean delete(Integer petID) throws PersistentException;
}
