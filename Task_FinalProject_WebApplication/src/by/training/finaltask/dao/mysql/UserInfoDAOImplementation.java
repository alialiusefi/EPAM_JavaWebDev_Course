package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.UserInfoDAO;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserInfoDAOImplementation extends BaseDAO implements UserInfoDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImplementation.class);
    private final String PROPERTY_PATH = "daomysqlqueries";

    public UserInfoDAOImplementation(Connection connection) {
        super(connection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    @Override
    public UserInfo get(Integer userID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getUserInfoDAO"))){
            preparedStatement.setInt(1,userID);
            try (ResultSet resultset = preparedStatement.executeQuery())
            {
                if(resultset.next())
                {
                    return new UserInfo(
                      resultset.getInt("id"),
                      resultset.getNString("firstname"),
                      resultset.getNString("lastname"),
                      resultset.getDate("dateofbirth"),
                            resultset.getNString("address")
                    );
                }
            }
        }
        catch (SQLException e)
        {
            LOGGER.warn(e.getMessage(),e);
            throw new PersistentException(e.getMessage(),e);
        }

    }

    @Override
    public List<UserInfo> getAll() throws PersistentException {
        return null;
    }

    @Override
    public boolean delete(Integer userID) throws PersistentException {
        return false;
    }

    @Override
    public boolean add(UserInfo element) throws PersistentException {
        return false;
    }

    //TODO: should i implement base methods?
    @Override
    public UserInfo get() throws PersistentException {
        return null;
    }

    @Override
    public boolean update(UserInfo element) throws PersistentException {
        return false;
    }

    @Override
    public boolean delete(UserInfo element) throws PersistentException {
        return false;
    }
}
