package by.training.finaltask.dao.mysql;


import by.training.finaltask.dao.daointerface.AdoptionDAO;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/*

    TODO: Correctly use try with resources
public List<User> getUser(int userId) {
    try (Connection con = DriverManager.getConnection(myConnectionURL);
         PreparedStatement ps = createPreparedStatement(con, userId);
         ResultSet rs = ps.executeQuery()) {

         // process the resultset here, all resources will be cleaned up

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


 */
public final class AdoptionDAOImplementation extends BaseDAO implements AdoptionDAO {

    private Logger LOGGER = LogManager.getLogger(AdoptionDAOImplementation.class);


    public AdoptionDAOImplementation(Connection connection) {
        super(connection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }

    @Override
    public Adoption get(Integer petID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAdoptionDAO"))) {
            preparedStatement.setInt(1, petID);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                if (resultset.next()) {
                    GregorianCalendar adoptionStart = new GregorianCalendar();
                    adoptionStart.setTime(resultset.getDate(
                            "adoption_start"));
                    Date adoptionEndSqlDate = resultset.getDate(
                            "adoption_end");
                    GregorianCalendar adoptionEnd = new GregorianCalendar();
                    if (adoptionEndSqlDate != null) {
                        adoptionEnd.setTime(adoptionEndSqlDate);
                    }
                    return new by.training.finaltask.entity.Adoption(
                            resultset.getInt(1),
                            adoptionStart,
                            adoptionEnd,
                            resultset.getInt(4)
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
    public List<Adoption> getAll() throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllAdoptionDAO"))) {
            List<Adoption> adoptions = new LinkedList<>();
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    GregorianCalendar adoptionStart = new GregorianCalendar();
                    adoptionStart.setTime(resultset.getDate(
                            "adoption_start"));
                    GregorianCalendar adoptionEnd = new GregorianCalendar();
                    adoptionEnd.setTime(resultset.getDate(
                            "adoption_end"));
                    adoptions.add(new by.training.finaltask.entity.Adoption(
                            resultset.getInt(1),
                            adoptionStart,
                            adoptionEnd,
                            resultset.getInt(4)
                    ));
                }

                return adoptions;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public List<Adoption> getAllByID(Integer petID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("getAllAdoptionDAO"))) {
            List<Adoption> adoptions = new LinkedList<>();
            preparedStatement.setInt(1, petID);
            try (ResultSet resultset = preparedStatement.executeQuery()) {
                while (resultset.next()) {
                    GregorianCalendar adoptionStart = new GregorianCalendar();
                    adoptionStart.setTime(resultset.getDate(
                            "adoption_start"));
                    GregorianCalendar adoptionEnd = new GregorianCalendar();
                    adoptionEnd.setTime(resultset.getDate(
                            "adoption_end"));
                    adoptions.add(new by.training.finaltask.entity.Adoption(
                            resultset.getInt(1),
                            adoptionStart,
                            adoptionEnd,
                            resultset.getInt(4)
                    ));
                }

                return adoptions;
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(Integer petID) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("deleteAdoptionDAO"))) {
            preparedStatement.setInt(1, petID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        } finally {
            LOGGER.debug("AdoptionDAO Record Deleted!");
        }
    }

    @Override
    public int add(Adoption element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("addAdoptionDAO"), PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, element.getPetID());
            Date sqlDateAdoptionStart = new Date(
                    element.getAdoption_start().getTimeInMillis());
            Date sqlDateAdoptionend = null;
            if (element.getAdoption_end() != null) {
                sqlDateAdoptionend = new Date(
                        element.getAdoption_end().getTimeInMillis());
            }
            preparedStatement.setDate(2, sqlDateAdoptionStart);
            preparedStatement.setDate(3, sqlDateAdoptionend);
            preparedStatement.setInt(4, element.getUserID());
            preparedStatement.executeUpdate();
            try (ResultSet set = preparedStatement.getGeneratedKeys()) {
                return set.getInt(1);
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage(), e);
                throw new PersistentException(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't add row!\n" + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Adoption element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                resourceBundle.getString("updateAdoptionDAO"))) {
            preparedStatement.setInt(1, element.getPetID());
            Date sqlDateAdoptionStart = new Date(
                    element.getAdoption_start().getTimeInMillis());
            Date sqlDateAdoptionend = null;
            if (element.getAdoption_end() != null) {
                sqlDateAdoptionend = new Date(
                        element.getAdoption_end().getTimeInMillis());
            }
            preparedStatement.setDate(2, sqlDateAdoptionStart);
            preparedStatement.setDate(3, sqlDateAdoptionend);
            preparedStatement.setInt(4, element.getUserID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException("Couldn't update user!\n" +
                    e.getMessage(), e);
        }
    }

    //todo: ask how to delete adoptions records using an adoption! delete IS STILL NOT DONE
    @Override
    public boolean delete(Adoption element) throws PersistentException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(resourceBundle.getString("deleteByElementAdoptionDAO"))) {
            preparedStatement.setInt(1, element.getPetID());
            Date sqlDateAdoptionStart = new Date(
                    element.getAdoption_start().getTimeInMillis());
            System.out.println(sqlDateAdoptionStart);
            preparedStatement.setDate(2, sqlDateAdoptionStart);
            Date sqlDateAdoptionend = null;
            if (element.getAdoption_end() != null) {
                sqlDateAdoptionend = new Date(
                        element.getAdoption_end().getTimeInMillis());
                preparedStatement.setDate(3, sqlDateAdoptionend);
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }
            preparedStatement.setInt(4, element.getUserID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new PersistentException(e.getMessage(), e);
        } finally {
            LOGGER.debug("User Deleted!");
        }
    }


    @Override
    public Adoption get() throws PersistentException {
        return null;
    }
}
