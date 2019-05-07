package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.*;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class TransactionImpl implements Transaction {
    private static Logger logger = LogManager.getLogger(TransactionImpl.class);

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    public BaseDAO createDao(DAOEnum daoEnum) throws PersistentException {
        if (daoEnum != null) {
                return createDAOInstance(daoEnum);
        }
        return null;
    }

    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("It is impossible to commit transaction", e);
            throw new PersistentException(e.getMessage(),e);
        }
    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("It is impossible to rollback transaction", e);
            throw new PersistentException(e.getMessage(),e);
        }
    }

    private BaseDAO createDAOInstance(DAOEnum daoEnum) {
        switch (daoEnum) {
            case ADOPTION:
                return new AdoptionDAOImplementation(connection);
            case PET:
                return new PetDAOImplementation(connection);
            case USER:
                return new UserDAOImplementation(connection);
            case USERINFO:
                return new UserInfoDAOImplementation(connection);
            default:
                return null;
        }
    }
}
