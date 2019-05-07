package by.training.finaltask.service;

import by.training.finaltask.dao.daointerface.Transaction;
import by.training.finaltask.dao.daointerface.TransactionFactory;
import by.training.finaltask.dao.mysql.*;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactoryImpl implements ServiceFactory {
	private static Logger logger = LogManager.getLogger(ServiceFactoryImpl.class);

	private TransactionFactory factory;

	public ServiceFactoryImpl(TransactionFactory factory) throws PersistentException {
		this.factory = factory;
	}

	@Override
	public Service getService(DAOEnum key) throws PersistentException {
		if(key != null) {
			try {
				Transaction transaction = factory.createTransaction();
				ServiceImpl service = createServiceInstance(key);
				service.setTransaction(transaction);

				InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
				return (Type)Proxy.newProxyInstance(classLoader, interfaces, handler);
			} catch(PersistentException e) {
				throw e;
			} catch(InstantiationException | IllegalAccessException e) {
				logger.error("Cannot create service class!", e);
				throw new PersistentException(e);
			}
		}
		return null;
	}

	private ServiceImpl createServiceInstance(DAOEnum daoEnum)
	{
		//todo: add services other than users
		switch (daoEnum) {
			case ADOPTION:
				return null;
			case PET:
				return null;
			case USER:
				return new UserServiceImpl();
			case USERINFO:
				return null;
			default:
				return null;
		}
	}

	@Override
	public void close() {
		factory.close();
	}
}
