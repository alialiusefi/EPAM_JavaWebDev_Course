package by.training.finaltask.dao.daointerface;

import by.training.finaltask.dao.mysql.BaseDAO;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.exception.PersistentException;

public interface Transaction {

    BaseDAO createDao(DAOEnum daoenum) throws PersistentException;

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
