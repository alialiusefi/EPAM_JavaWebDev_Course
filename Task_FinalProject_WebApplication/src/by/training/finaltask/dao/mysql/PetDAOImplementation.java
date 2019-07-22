package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.PetDAO;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public final class PetDAOImplementation extends BaseDAO implements PetDAO {

    private Logger LOGGER = LogManager.getLogger(PetDAOImplementation.class);


    public PetDAOImplementation(Connection connection) {
        super(connection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    @Override
    public Pet get(int ID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getPetDAO"))) {
            preparedStatement.setInt(1, ID);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                if (resultset.next()) {
                    GregorianCalendar dateofbirth = new GregorianCalendar();
                    dateofbirth.setTime(resultset.getDate(
                            "dateofbirth"));
                    GregorianCalendar datesheltered = new GregorianCalendar();
                    datesheltered.setTime(resultset.getDate(
                            "date_sheltered"));
                    PetStatus petStatus =
                            PetStatus.valueOf(resultset.getNString("status").toUpperCase());
                    return new Pet(
                            resultset.getInt("id"),
                            resultset.getNString("name"),
                            resultset.getBlob("photo"),
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
    public List<Pet> getAllSheltered(int offset, int rowcount) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllShelteredPetDAO"))) {
            preparedStatement.setInt(1,offset);
            preparedStatement.setInt(2,rowcount);
            List<Pet> pets = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    GregorianCalendar dateofbirth = new GregorianCalendar();
                    dateofbirth.setTime(resultset.getDate(
                            "dateofbirth"));
                    GregorianCalendar datesheltered = new GregorianCalendar();
                    datesheltered.setTime(resultset.getDate(
                            "date_sheltered"));
                    PetStatus petStatus =
                            PetStatus.valueOf(resultset.getNString("status").toUpperCase());
                    pets.add(new Pet(
                            resultset.getInt("id"),
                            resultset.getNString("name"),
                            resultset.getBlob("photo"),
                            dateofbirth,
                            resultset.getDouble("weight"),
                            datesheltered,
                            resultset.getInt("shelter_id"),
                            resultset.getInt("breed_id"),
                            petStatus
                    ));
                }
                return pets;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public List<Pet> getAll(int offset, int rowcount) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllPetDAO"))) {
            preparedStatement.setInt(1,offset);
            preparedStatement.setInt(2,rowcount);
            List<Pet> pets = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    GregorianCalendar dateofbirth = new GregorianCalendar();
                    dateofbirth.setTime(resultset.getDate(
                            "dateofbirth"));
                    GregorianCalendar datesheltered = new GregorianCalendar();
                    datesheltered.setTime(resultset.getDate(
                            "date_sheltered"));
                    PetStatus petStatus =
                            PetStatus.valueOf(resultset.getNString("status").toUpperCase());
                    pets.add(new Pet(
                            resultset.getInt("id"),
                            resultset.getNString("name"),
                            resultset.getBlob("photo"),
                            dateofbirth,
                            resultset.getDouble("weight"),
                            datesheltered,
                            resultset.getInt("shelter_id"),
                            resultset.getInt("breed_id"),
                            petStatus
                    ));
                }

                return pets;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Integer ID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("deletePetDAO"))) {
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public int getAmountOfAllPets() throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAmountAllPetDAO"))) {
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
    public int getAmountOfAllShelteredPets() throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAmountAllShelteredPetDAO"))) {
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
    public Integer add(Pet element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("addPetDAO"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,element.getId());
            preparedStatement.setNString(2,element.getName());
            preparedStatement.setBlob(3,element.getPhoto());
            Date dateOfBirth = new Date(element.getDateOfBirth().getTimeInMillis());
            preparedStatement.setDate(4,dateOfBirth);
            preparedStatement.setDouble(5,element.getWeight());
            Date dateSheltered = new Date(
                    element.getDateSheltered().getTimeInMillis());
            preparedStatement.setDate(6,dateSheltered);
            preparedStatement.setInt(7,element.getShelterID());
            preparedStatement.setInt(8,element.getBreedID());
            preparedStatement.setNString(9,element.getStatus().getValue());
            preparedStatement.executeUpdate();
            try(ResultSet set = preparedStatement.getGeneratedKeys())
            {
                set.next();
                return set.getInt(1);
            } catch (SQLException e)
            {
                LOGGER.warn(e.getMessage(),e);
                throw new PersistentException(e.getMessage(),e);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't add row!\n" + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Pet element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("updatePetDAO"))) {
            preparedStatement.setInt(1,element.getId());
            preparedStatement.setNString(2,element.getName());
            preparedStatement.setBlob(3,element.getPhoto());
            Date dateOfBirth = new Date(element.getDateOfBirth().getTimeInMillis());
            preparedStatement.setDate(4,dateOfBirth);
            preparedStatement.setDouble(5,element.getWeight());
            Date dateSheltered = new Date(
                    element.getDateSheltered().getTimeInMillis());
            preparedStatement.setDate(6,dateSheltered);
            preparedStatement.setInt(7,element.getShelterID());
            preparedStatement.setInt(8,element.getBreedID());
            preparedStatement.setNString(9,element.getStatus().getValue());
            preparedStatement.setInt(10,element.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't update user!\n" +
                    e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Pet element) throws PersistentException {
        //implementation here
        return false;
    }

    @Override
    public Pet get() throws PersistentException {
        return get(1);
    }
}
