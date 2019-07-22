package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.ShelterDAO;
import by.training.finaltask.entity.Shelter;
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

public class ShelterDAOImplementation extends BaseDAO implements ShelterDAO {

    private static final Logger LOGGER = LogManager.getLogger(ShelterDAOImplementation.class);

    public ShelterDAOImplementation(Connection aliveConnection) {
        super(aliveConnection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    @Override
    public List<Shelter> getAll() throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllShelters"))) {
            List<Shelter> shelters = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    Integer id = resultset.getInt("id");
                    String name = resultset.getNString("name");
                    String location = resultset.getNString("location");
                    shelters.add(new Shelter(id, name, location));
                }
                return shelters;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public Shelter getByID(Integer id) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getShelterByID"))) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                resultset.next();
                int shelterID = resultset.getInt("id");
                String name = resultset.getNString("name");
                String location = resultset.getNString("location");
                return new Shelter(shelterID, name, location);

            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public int add(Shelter element) throws PersistentException {
        return 0;
    }

    @Override
    public Shelter get() throws PersistentException {
        return null;
    }

    @Override
    public boolean update(Shelter element) throws PersistentException {
        return false;
    }

    @Override
    public boolean delete(Shelter element) throws PersistentException {
        return false;
    }
}
