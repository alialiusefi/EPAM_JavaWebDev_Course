package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.PetDAO;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

public class PetDAOImplementation extends BaseDAO implements PetDAO {

    private Logger LOGGER = LogManager.getLogger(PetDAOImplementation.class);
    private final String PROPERTY_PATH = "daomysqlqueries";

    public PetDAOImplementation(Connection connection) {
        super(connection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    @Override
    public Pet get(Integer ID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getPetDAO"))) {
            preparedStatement.setInt(1, ID);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                if (resultset.next()) {
                    GregorianCalendar dateofbirth = new GregorianCalendar();
                    dateofbirth.setTime(resultset.getDate(
                            "dateofbirth"));
                    GregorianCalendar datesheltered = new GregorianCalendar();
                    datesheltered.setTime( resultset.getDate(
                            "date_sheltered"));
                    PetStatus petStatus =
                            PetStatus.valueOf(resultset.getNString("status").toUpperCase());
                    return new Pet(
                            resultset.getInt("id"),
                            resultset.getNString("name"),
                            resultset.getNString("photourl"),
                            dateofbirth,
                            resultset.getDouble("weight"),
                            datesheltered,
                            resultset.getInt("shelter_id"),
                            resultset.getInt("breed_id"),
                            petStatus
                            );
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Pet> getAll() throws PersistentException {
        return null;
    }

    @Override
    public boolean delete(Integer ID) throws PersistentException {
        return false;
    }

    @Override
    public boolean add(Pet element) throws PersistentException {
        return false;
    }

    @Override
    public Pet get() throws PersistentException {
        return null;
    }

    @Override
    public boolean update(Pet element) throws PersistentException {
        return false;
    }

    @Override
    public boolean delete(Pet element) throws PersistentException {
        return false;
    }
}
