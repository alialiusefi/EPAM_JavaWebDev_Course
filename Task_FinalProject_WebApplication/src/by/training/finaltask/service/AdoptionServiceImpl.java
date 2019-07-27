package by.training.finaltask.service;

import by.training.finaltask.dao.daointerface.AdoptionDAO;
import by.training.finaltask.dao.daointerface.PetDAO;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.AdoptionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class AdoptionServiceImpl extends ServiceImpl implements AdoptionService {

    public AdoptionServiceImpl(Connection aliveConnection) {
        super(aliveConnection);
    }

    @Override
    public Adoption get(int adoptionID) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
            Adoption adoption = dao.get(adoptionID);
            commit();
            connection.setAutoCommit(true);
            return adoption;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public List<Adoption> getAll(int offset, int rowcount) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
            List<Adoption> adoptions = dao.getAll(offset, rowcount);
            commit();
            connection.setAutoCommit(true);
            return adoptions;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public int getAllCount() throws PersistentException {
        try {
            connection.setAutoCommit(false);
            AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
            int res = dao.getAllCount();
            commit();
            connection.setAutoCommit(true);
            return res;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public Integer add(Adoption adoption) throws PersistentException, InvalidFormDataException {
        try {
            connection.setAutoCommit(false);
            AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
            validateDate(adoption);
            isOverlapping(adoption);
            int res = dao.add(adoption);
            updatePetToAdopted(adoption);
            commit();
            connection.setAutoCommit(true);
            return res;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(Adoption adoption) throws PersistentException,InvalidFormDataException {
        try {
            connection.setAutoCommit(false);
            AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
            validateDate(adoption);
            isOverlapping(adoption);
            dao.update(adoption);
            updatePetToAdopted(adoption);
            commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(int adoptionID) throws InvalidFormDataException, PersistentException {
        try {
            AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
            isExpired(adoptionID);
            connection.setAutoCommit(false);
            dao.delete(adoptionID);
            commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    private void validateDate(Adoption adoption) throws InvalidFormDataException {
        Calendar calendar = Calendar.getInstance();
        if (adoption.getAdoptionEnd() != null) {
            if (adoption.getAdoptionStart().compareTo(adoption.getAdoptionEnd()) > 0
            || adoption.getAdoptionEnd().compareTo(calendar) < 0
            ) {
                throw new InvalidFormDataException("incorrectDateFormat");
            }
        }
        if (adoption.getAdoptionStart().compareTo(calendar) < 0) {
            throw new InvalidFormDataException("incorrectDateFormat");
        }
    }

    private void isOverlapping(Adoption adoption)
            throws InvalidFormDataException, PersistentException {

        AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
        int count = 0;
        if (adoption.getAdoptionEnd() != null) {
            count = dao.getCountByPetIDandDateNotNull(adoption.getPetID(),
                    adoption.getAdoptionStart(), adoption.getAdoptionEnd());
        } else {
            count = dao.getCountByPetIDandDateNull(adoption.getPetID(),
                    adoption.getAdoptionStart());
        }
        if (count != 0) {
            throw new InvalidFormDataException("petAlreadyAdopted");
        }
    }

    private void updatePetToAdopted(Adoption adoption) throws PersistentException {
        Calendar calendar = Calendar.getInstance();
        /*TODO; test this*/
        if (calendar.compareTo(adoption.getAdoptionStart()) == 0) {
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            Pet pet = dao.get(adoption.getPetID());
            pet.setStatus(PetStatus.ADOPTED);
            dao.update(pet);
        }
    }

    private void isExpired(int adoptionID) throws InvalidFormDataException, PersistentException{
        Adoption adoption = get(adoptionID);
        Calendar calendar = Calendar.getInstance();
        if(adoption.getAdoptionStart().compareTo(calendar) < 0)
        {
            throw new InvalidFormDataException("cannotDeleteExpiredAdoption");
        }
    }

}
