package by.training.finaltask.dao.daointerface;

import by.training.finaltask.exception.PersistentException;


public interface DAO<T> {

    boolean add(T element) throws PersistentException;
    T get() throws PersistentException;
    boolean update(T element) throws PersistentException;
    boolean delete(T element) throws PersistentException;

}
