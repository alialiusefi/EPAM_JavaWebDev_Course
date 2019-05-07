package by.training.finaltask.service;

import by.training.finaltask.exception.PersistentException;

public interface ServiceFactory {
	<Type extends Service> Type getService(Class<Type> key) throws PersistentException;

	void close();
}
