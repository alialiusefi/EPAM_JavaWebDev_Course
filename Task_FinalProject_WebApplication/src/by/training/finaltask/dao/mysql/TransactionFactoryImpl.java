package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.Transaction;
import by.training.finaltask.dao.daointerface.TransactionFactory;
import by.training.finaltask.dao.pool.ConnectionPool;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
	private static Logger logger = LogManager.getLogger(TransactionFactoryImpl.class);
	private Connection connection;
	
	public TransactionFactoryImpl() throws PersistentException {
		connection = ConnectionPool.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
		} catch(SQLException e) {
			logger.error("Cannot turn off auto-commit!", e);
			throw new PersistentException(e.getMessage(),e);
		}
	}

	@Override
	public Transaction createTransaction() throws PersistentException {
		return new TransactionImpl(connection);
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch(SQLException e) {}
	}
}
