package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.UserDAO;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * user_id
 * username
 * password
 * role
 */


public class UserDAOImplementation extends BaseDAO implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImplementation.class);
    private final String PROPERTY_PATH = "daoqueries";

    // todo: check this later!
    public UserDAOImplementation(Connection connection) {
        super(connection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    @Override
    public User get(Integer userID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getUserDAO"))) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String username = resultSet.getNString("username");
                    String password = resultSet.getNString("password");
                    Role role = Role.valueOf(resultSet.getInt(4));
                    return new User(id, username, password, role);
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        } finally {
            LOGGER.debug("debug statements here!");
        }
        return null;
    }

    @Override
    public List<User> getAll() throws PersistentException {
        List<User> userList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllUserDAO"))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String username = resultSet.getNString("username");
                    String password = resultSet.getNString("password");
                    Role role = Role.valueOf(resultSet.getInt(4));
                    userList.add(new User(id, username, password, role));
                }
            }
            return userList;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        } finally {
            LOGGER.debug("debug statements here!");
        }
    }

    @Override
    public boolean delete(Integer userID) throws PersistentException {
        return false;
    }

    @Override
    public boolean add(User element) throws PersistentException {
        return false;
    }

    // gets first element
    @Override
    public User get() throws PersistentException {
        return null;
    }


    @Override
    public boolean update(User element) throws PersistentException {
        return false;
    }

    @Override
    public boolean delete(User element) throws PersistentException {
        return false;
    }
}
