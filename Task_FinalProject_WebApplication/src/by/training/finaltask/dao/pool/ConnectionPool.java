package by.training.finaltask.dao.pool;

import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {

    private static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private String URL;
    private String userName;
    private String password;
    private int timeoutConnectionLimit;
    private ConcurrentLinkedDeque<PetPooledConnection> availableConnections
            = new ConcurrentLinkedDeque<>();
    private ConcurrentLinkedDeque<PetPooledConnection> usedConnections
            = new ConcurrentLinkedDeque<>();
    private int maxConnections;
    private ReentrantLock classReentrantLock = new ReentrantLock();
    private static ConnectionPool INSTANCE = null;

    public static ConnectionPool getInstance() {
        ReentrantLock reentrantLock = new ReentrantLock();
        if (INSTANCE == null) {
            reentrantLock.lock();
            try {
                INSTANCE = new ConnectionPool();
            } finally {
                reentrantLock.unlock();
            }
        }
        return INSTANCE;
    }


    public Connection getConnection() throws PersistentException {
        PetPooledConnection connection = null;
        while (connection == null) {
            try {
                    if (!availableConnections.isEmpty()) {
                        connection = availableConnections.pop();
                        if (!connection.isValid(timeoutConnectionLimit)) {
                            try {
                                connection.getConnection().close();
                            } catch (SQLException e) {

                            }
                            connection = null;
                        }
                    } else if (usedConnections.size() < maxConnections){
                        connection = createNewConnection();
                    } else {
                     LOGGER.error("The limit of number of database connections is exceeded");
                     throw new PersistentException();
                    }
            } catch (SQLException e) {
                LOGGER.error("Cannot connect to database", e);
                throw new PersistentException(e.getMessage(), e);
            }
        }
        usedConnections.add(connection);
        LOGGER.debug("Connection was used, Available connections:" + availableConnections.size()
                + " Busy Connections: " + usedConnections.size()
        );
        return connection;
    }

    public void initialize(String driverClass, String URL, String userName,
                           String password, int startConnections, int maxConnections,
                           int timeout) throws PersistentException {
        try {
            destroy();
            Class.forName(driverClass);
            this.URL = URL;
            this.maxConnections = maxConnections;
            this.userName = userName;
            this.password = password;
            this.timeoutConnectionLimit = timeout;
            for (int i = 0; i < startConnections; i++) {
                availableConnections.add(createNewConnection());
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Couldn't create initial available Connections to database!", e);
            throw new PersistentException(e.getMessage(), e);
        }

    }

    private PetPooledConnection createNewConnection() throws SQLException, PersistentException {
        if ((usedConnections.size() + availableConnections.size()) < maxConnections) {
            return new PetPooledConnection(DriverManager.getConnection(this.URL, this.userName, this.password));
        }
        throw new PersistentException(
                "Cannot create connections more than max. amount of connections");
    }

    public void freeConnection(PetPooledConnection connection) {
        try {
            if (connection.isValid(timeoutConnectionLimit)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                availableConnections.add(connection);
                LOGGER.debug("Connection Cleared.");
            }
        } catch (SQLException e) {
            LOGGER.error("Couldn't clear connection!", e);
            try {
                connection.close();
            } catch (SQLException e2) {
                LOGGER.error(e2.getMessage(), e2);
            }
        }
    }

    public void destroy() {
        if(availableConnections!=null && usedConnections !=null)
        {
            usedConnections.addAll(availableConnections);
            availableConnections.clear();
            for (PetPooledConnection connection : usedConnections) {
                try {
                    connection.getConnection().close();
                } catch (SQLException e) {
                    LOGGER.warn(e.getMessage(), e);
                }
            }
            usedConnections.clear();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

}
