package by.training.finaltask.dao.daointerface;

import by.training.finaltask.exception.PersistentException;

public interface Transaction {

    <T extends DAO<?>> T createDao(Class<T> key) throws PersistentException;

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
