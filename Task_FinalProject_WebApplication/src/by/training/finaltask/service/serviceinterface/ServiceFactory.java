package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.exception.PersistentException;

import java.sql.Connection;

public interface ServiceFactory {

	Service createService(DAOEnum key) throws PersistentException;

	Connection getConnecction();
	void close() throws PersistentException;
}
