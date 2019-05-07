package by.training.finaltask.service;

import by.training.finaltask.dao.daointerface.Transaction;


abstract public class ServiceImpl implements Service {
    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
