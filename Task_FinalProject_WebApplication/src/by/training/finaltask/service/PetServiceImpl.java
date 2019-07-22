package by.training.finaltask.service;

import by.training.finaltask.dao.daointerface.PetDAO;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.PetService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PetServiceImpl extends ServiceImpl implements PetService {


    public PetServiceImpl(Connection connection)
    {
        super(connection);
    }

    @Override
    public List<Pet> getAll(int offset, int rowcount) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            List<Pet> allPets = dao.getAll(offset,rowcount);
            commit();
            connection.setAutoCommit(true);
            return allPets;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public Pet get(int ID) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            Pet pet = dao.get(ID);
            commit();
            connection.setAutoCommit(true);
            return pet;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public List<Pet> getAllSheltered(int offset, int rowcount) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            List<Pet> allPets = dao.getAllSheltered(offset,rowcount);
            commit();
            connection.setAutoCommit(true);
            return allPets;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public int getAmountOfAllPets() throws PersistentException {
        try {
            connection.setAutoCommit(false);
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            int amountOfAllPets = dao.getAmountOfAllPets();
            commit();
            connection.setAutoCommit(true);
            return amountOfAllPets;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public int getAmountOfAllShelteredPets() throws PersistentException {
        try {
            connection.setAutoCommit(false);
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            int result = dao.getAmountOfAllShelteredPets();
            commit();
            connection.setAutoCommit(true);
            return result;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public Integer add(Pet pet) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            int idGenerated = dao.add(pet);
            commit();
            connection.setAutoCommit(true);
            return idGenerated;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(Pet pet) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            dao.update(pet);
            commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(int ID) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            PetDAO dao = (PetDAO) createDao(DAOEnum.PET);
            dao.delete(ID);
            commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }
}
