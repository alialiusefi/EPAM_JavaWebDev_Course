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
import java.util.List;

public class AdoptionServiceImpl extends ServiceImpl implements AdoptionService {

    public AdoptionServiceImpl(Connection aliveConnection) {
        super(aliveConnection);
    }

    @Override
    public Adoption get(int ID) throws PersistentException {
        return null;
    }

    @Override
    public List<Adoption> getAll(int offset, int rowcount) throws PersistentException {
        return null;
    }

    @Override
    public Integer add(Adoption adoption) throws PersistentException, InvalidFormDataException {
        try {
            connection.setAutoCommit(false);
            AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
            validateDate(adoption);
            checkForExistingAdoptions(adoption);
            int res = dao.add(adoption);
            changePetStatusToAdopted(adoption);
            commit();
            connection.setAutoCommit(true);
            return res;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(Adoption adoption) throws PersistentException {

    }

    @Override
    public void delete(int ID) throws PersistentException {

    }

    private void validateDate(Adoption adoption) throws InvalidFormDataException {
        if (adoption.getAdoption_start().compareTo(adoption.getAdoption_end()) > 0) {
            throw new InvalidFormDataException("incorrectDateFormat");
        }
    }

    private void checkForExistingAdoptions(Adoption adoption)
            throws InvalidFormDataException, PersistentException {

        AdoptionDAO dao = (AdoptionDAO) createDao(DAOEnum.ADOPTION);
        int count = dao.getCountByPetIDandDate(adoption.getPetID(),
                adoption.getAdoption_start(), adoption.getAdoption_end());
        if (count != 0) {
            throw new InvalidFormDataException("petAlreadyAdopted");
        }
    }

    private void changePetStatusToAdopted(Adoption adoption) throws PersistentException {
        PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
        Pet pet = dao.get(adoption.getPetID());
        pet.setStatus(PetStatus.ADOPTED);
        dao.update(pet);
    }

}
