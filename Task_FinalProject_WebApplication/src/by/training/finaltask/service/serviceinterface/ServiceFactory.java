package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.Service;

public interface ServiceFactory {

	Service createService(DAOEnum key) throws PersistentException;

	void close() throws PersistentException;
}
