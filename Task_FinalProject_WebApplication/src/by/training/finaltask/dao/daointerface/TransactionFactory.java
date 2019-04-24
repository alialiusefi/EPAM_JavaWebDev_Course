package by.training.finaltask.dao.daointerface;

import by.training.finaltask.exception.PersistentException;

public interface TransactionFactory {

    Transaction createTransaction() throws PersistentException;

    void close();


}
