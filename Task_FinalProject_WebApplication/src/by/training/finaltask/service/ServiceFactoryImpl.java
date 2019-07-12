package by.training.finaltask.service;

import by.training.finaltask.dao.mysql.*;
import by.training.finaltask.dao.pool.ConnectionPool;
import by.training.finaltask.dao.pool.PetPooledConnection;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.Service;
import by.training.finaltask.service.serviceinterface.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceFactoryImpl implements ServiceFactory {

    private static Logger logger = LogManager.getLogger(ServiceFactoryImpl.class);
    private Connection aliveConnection;

    public ServiceFactoryImpl() throws PersistentException {
        try {
            aliveConnection = ConnectionPool.getInstance().getConnection();
            aliveConnection.setAutoCommit(false);
        } catch(SQLException e) {
            logger.error("Cannot turn off auto-commit!", e);
            throw new PersistentException(e.getMessage(),e);
        }
    }

    @Override
    public Service createService(DAOEnum key) throws PersistentException {
        if (key != null) {
            ServiceImpl service = createServiceInstance(key,aliveConnection);
            return service;
        }
        return null;
    }

    public Connection getConnection() {
        return aliveConnection;
    }

    private ServiceImpl createServiceInstance(DAOEnum daoEnum, Connection aliveConnection) {

        switch (daoEnum) {
            case ADOPTION:
                return null;
            case PET:
                return null;
            case USER:
                return new UserServiceImpl(aliveConnection);
            case USERINFO:
                return new UserInfoServiceImpl(aliveConnection);
            default:
                throw new IllegalArgumentException("Cannot create service instance!");
        }
    }

    @Override
    public void close() throws PersistentException {
        try {
            aliveConnection.close();
            ConnectionPool.getInstance().freeConnection((PetPooledConnection) aliveConnection);
        } catch (SQLException e) {
            logger.warn("Cannot close connection! \n" + e.getMessage(),e);
            throw new PersistentException(e.getMessage(),e);
        }
    }
}
