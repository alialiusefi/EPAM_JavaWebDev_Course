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
    private ConcurrentLinkedDeque<PetPooledConnection> availableConnections = new ConcurrentLinkedDeque<>();
    private ConcurrentLinkedDeque<PetPooledConnection> busyConnections = new ConcurrentLinkedDeque<>();
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
                    } else {
                        connection = createNewConnection();
                    }

            } catch (SQLException e) {
                LOGGER.error("Cannot connect to database", e);
                throw new PersistentException(e.getMessage(), e);
            }
        }
        busyConnections.add(connection);
        LOGGER.debug("Connection was used, Available connections:" + availableConnections.size()
                + " Busy Connections: " + busyConnections.size()
        );
        return connection;
    }


    /*TQuestion: Why do we create a certain method init..() instead of doing all of this in constructor
      Possible answer: We can't initialize using out constructor, since the class is a singleton
    */
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
        if ((busyConnections.size() + availableConnections.size()) < maxConnections) {
            return new PetPooledConnection(DriverManager.getConnection(this.URL, this.userName, this.password));
        }
        throw new PersistentException("Cannot create connections more than max. amount of connections");
    }

    public void clearConnection(PetPooledConnection connection) {
        try {
            if (connection.isValid(timeoutConnectionLimit)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                busyConnections.remove(connection);
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
        if(availableConnections!=null && busyConnections!=null)
        {
            busyConnections.addAll(availableConnections);
            availableConnections.clear();
            for (PetPooledConnection connection : busyConnections) {
                try {
                    connection.getConnection().close();
                } catch (SQLException e) {
                    LOGGER.warn(e.getMessage(), e);
                }
            }
            busyConnections.clear();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }

}
