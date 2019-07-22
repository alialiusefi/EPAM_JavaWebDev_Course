package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.BreedDAO;
import by.training.finaltask.entity.Breed;
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

public class BreedDAOImplementation extends BaseDAO implements BreedDAO {

    private Logger LOGGER = LogManager.getLogger(PetDAOImplementation.class);

    public BreedDAOImplementation(Connection connection) {
        super(connection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    public List<Breed> getAll() throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllBreeds"))) {
            List<Breed> breeds = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    Integer id = resultset.getInt("id");
                    String name = resultset.getNString("name");
                    String description = resultset.getNString("description");
                    String origin = resultset.getNString("origin");
                    breeds.add(new Breed(id, name, description, origin));
                }
                return breeds;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public Breed getByID(Integer ID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getBreedByID"))) {
                preparedStatement.setInt(1,ID);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                resultset.next();
                Integer id = resultset.getInt("id");
                String breedName = resultset.getNString("name");
                String description = resultset.getNString("description");
                String origin = resultset.getNString("origin");
                return new Breed(id, breedName, description, origin);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public Breed get() throws PersistentException {
        return null;
    }

    @Override
    public int add(Breed element) throws PersistentException {
        return 0;
    }

    @Override
    public boolean update(Breed element) throws PersistentException {
        return false;
    }

    @Override
    public boolean delete(Breed element) throws PersistentException {
        return false;
    }
}
