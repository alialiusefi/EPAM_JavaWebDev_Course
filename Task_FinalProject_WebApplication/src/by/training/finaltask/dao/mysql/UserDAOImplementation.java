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

public final class UserDAOImplementation extends BaseDAO implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImplementation.class);

    public UserDAOImplementation(Connection connection) {
        super(connection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    @Override
    public User get() throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getUserDAO"))) {
            preparedStatement.setInt(1, 1);
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
        }
        //TODO: Question: Should i throw an exception instead of null
        return null;
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
        }
        //TODO: Question: Should i throw an exception instead of null
        return null;
    }

    @Override
    public User get(String user) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getUserByUserNameDAO"))) {
            preparedStatement.setNString(1, user);
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
        }
        //TODO: Question: Should i throw an exception instead of null
        return null;
    }

    @Override
    public User get(String user, String pass) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getUserByUserNamePassWordDAO"))) {
            preparedStatement.setNString(1, user);
            preparedStatement.setNString(2, pass);
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
        }
        //  TODO: Question: Should i throw an exception instead of null
        return null;
    }

    @Override
    public List<User> getAll(int offset, int rowcount) throws PersistentException {
        List<User> userList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllUserDAO"))) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, rowcount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
        }
    }

    @Override
    public boolean delete(Integer userID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("deleteUserDAO"))) {
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);

        }
    }

    @Override
    public Integer add(User element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("addUserDAO"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setNString(1, element.getUsername());
            preparedStatement.setNString(2, element.getPassword());
            preparedStatement.setInt(3, element.getUserRole().getValue());
            preparedStatement.executeUpdate();
            try (ResultSet set = preparedStatement.getGeneratedKeys()) {
                if (set.next()) {
                    return set.getInt(1);
                }
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage(), e);
                throw new PersistentException("Couldn't get generated keys!\n "
                        + e.getMessage(), e);
            }
            return -1;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't add row!\n" + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(User element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("updateUserDAO"))) {
            preparedStatement.setNString(1, element.getUsername());
            preparedStatement.setNString(2, element.getPassword());
            preparedStatement.setInt(3, element.getUserRole().getValue());
            preparedStatement.setInt(4, element.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't update user!\n" +
                    e.getMessage(), e);
        }

    }

    @Override
    public boolean delete(User element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("deleteUserDAO"))) {
            preparedStatement.setInt(1, element.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public int getAmountOfAllStaff() throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAmountAllStaffDAO"))) {
            try (ResultSet res = preparedStatement.executeQuery()) {
                res.next();
                return res.getInt(1);
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage(), e);
                throw new PersistentException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> getAllStaff(int offset, int rowcount) throws PersistentException {
        List<User> userList = new LinkedList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllStaffDAO"))) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, rowcount);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
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
        }
    }

}
