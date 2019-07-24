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
                    return getPet(resultset);
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
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, rowcount);
            List<Pet> pets = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    pets.add(getPet(resultset));
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
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, rowcount);
            List<Pet> pets = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    pets.add(getPet(resultset));
                }
                return pets;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public List<Pet> getAllByShelter(PetStatus status, int shelterID, int offset, int rowcount)
            throws PersistentException {
        String query = resourceBundle.getString("getAllPetByShelterDAO");
        if (status != null) {
            query = addPetStatus(query);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shelterID);
            if (status != null) {
                preparedStatement.setNString(2, status.getValue());
                preparedStatement.setInt(3, offset);
                preparedStatement.setInt(4, rowcount);
            } else {
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, rowcount);
            }
            List<Pet> pets = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    pets.add(getPet(resultset));
                }
                return pets;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public List<Pet> getAllByBreed(PetStatus status, int breedID, int offset, int rowcount)
            throws PersistentException {
        String query = resourceBundle.getString("getAllPetByBreedDAO");
        if (status != null) {
            query = addPetStatus(query);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, breedID);
            if (status != null) {
                preparedStatement.setNString(2, status.getValue());
                preparedStatement.setInt(3, offset);
                preparedStatement.setInt(4, rowcount);
            } else {
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, rowcount);
            }
            List<Pet> pets = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    pets.add(getPet(resultset));
                }
                return pets;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public List<Pet> getAllByBirthDate(int relation, PetStatus status,
                                       GregorianCalendar calendar, int offset, int rowcount)
            throws PersistentException {
        String query = resourceBundle.getString("getAllPetByBirthDateDAO");
        if (status != null) {
            query = addPetStatus(query);
        }
        query = addRelation(query, relation);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Date date = new Date(calendar.getTimeInMillis());
            preparedStatement.setDate(1, date);
            if (status != null) {
                preparedStatement.setNString(2, status.getValue());
                preparedStatement.setInt(3, offset);
                preparedStatement.setInt(4, rowcount);
            } else {
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, rowcount);
            }
            List<Pet> pets = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    pets.add(getPet(resultset));
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
    public int getAmountOfAllPetsByBirthDate(int relation, PetStatus status,
                                             GregorianCalendar calendar) throws PersistentException {
        String query = resourceBundle.getString("getAmountAllPetByBirthDateDAO");
        if (status != null) {
            query = addPetStatus(query);
        }
        query = addRelation(query, relation);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Date date = new Date(calendar.getTimeInMillis());
            preparedStatement.setDate(1, date);
            if (status != null) {
                preparedStatement.setNString(2, status.getValue());
            }
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
    public int getAmountOfAllPetsByShelter(PetStatus status, int shelterID) throws PersistentException {
        String query = resourceBundle.getString("getAmountAllPetByShelterDAO");
        if (status != null) {
            query = addPetStatus(query);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, shelterID);
            if (status != null) {
                preparedStatement.setNString(2, status.getValue());
            }
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
    public int getAmountOfAllPetsByBreed(PetStatus status, int breedID)
            throws PersistentException {
        String query = resourceBundle.getString("getAmountAllPetByBreedDAO");
        if (status != null) {
            query = addPetStatus(query);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, breedID);
            if (status != null) {
                preparedStatement.setNString(2, status.getValue());
            }
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
    public int add(Pet element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("addPetDAO"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            setPreparedStatement(preparedStatement, element);
            preparedStatement.executeUpdate();
            try (ResultSet set = preparedStatement.getGeneratedKeys()) {
                set.next();
                return set.getInt(1);
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage(), e);
                throw new PersistentException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't add row!\n"
                    + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Pet element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("updatePetDAO"))) {
            setPreparedStatement(preparedStatement, element);
            preparedStatement.setInt(10, element.getId());
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
        return delete(element.getId());
    }

    @Override
    public Pet get() throws PersistentException {
        return get(1);
    }

    private Pet getPet(ResultSet resultSet) throws SQLException {
        GregorianCalendar dateofbirth = new GregorianCalendar();
        dateofbirth.setTime(resultSet.getDate(
                "dateofbirth"));
        GregorianCalendar datesheltered = new GregorianCalendar();
        datesheltered.setTime(resultSet.getDate(
                "date_sheltered"));
        PetStatus petStatus =
                PetStatus.valueOf(resultSet.getNString("status").toUpperCase());
        return new Pet(
                resultSet.getInt("id"),
                resultSet.getNString("name"),
                resultSet.getBlob("photo"),
                dateofbirth,
                resultSet.getDouble("weight"),
                datesheltered,
                resultSet.getInt("shelter_id"),
                resultSet.getInt("breed_id"),
                petStatus);
    }

    private void setPreparedStatement(PreparedStatement preparedStatement,
                                      Pet element) throws SQLException {
        preparedStatement.setInt(1, element.getId());
        preparedStatement.setNString(2, element.getName());
        preparedStatement.setBlob(3, element.getPhoto());
        Date dateOfBirth = new Date(element.getDateOfBirth().getTimeInMillis());
        preparedStatement.setDate(4, dateOfBirth);
        preparedStatement.setDouble(5, element.getWeight());
        Date dateSheltered = new Date(
                element.getDateSheltered().getTimeInMillis());
        preparedStatement.setDate(6, dateSheltered);
        preparedStatement.setInt(7, element.getShelterID());
        preparedStatement.setInt(8, element.getBreedID());
        preparedStatement.setNString(9, element.getStatus().getValue());

    }

    private String addPetStatus(String query) {
        StringBuffer buffer = new StringBuffer(query);
        int idxOfLimit = buffer.indexOf("limit");
        if (idxOfLimit == -1) {
            buffer.insert(query.length() - 1, " and pets.status = ?");
            return buffer.toString();
        }
        buffer.insert(idxOfLimit - 1, " and pets.status = ? ");
        return buffer.toString();
    }

    private String addRelation(String query, int relation) {
        String colName = "dateofbirth";
        String greaterThan = " >= ";
        String lessThan = " <= ";
        StringBuffer buffer = new StringBuffer(query);
        int idxOfdateofbirth = buffer.lastIndexOf(colName);
        if (relation == 1) {
            buffer.insert(idxOfdateofbirth + colName.length(), greaterThan);
            return buffer.toString();
        } else {
            if (relation == -1) {
                buffer.insert(idxOfdateofbirth + colName.length(), lessThan);
                return buffer.toString();
            }
        }
        return query;
    }
}
