package by.training.finaltask.service;

import by.training.finaltask.dao.daointerface.BreedDAO;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Breed;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.BreedService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BreedServiceImpl extends ServiceImpl implements BreedService {

    public BreedServiceImpl(Connection aliveConnection)
    {
        super(aliveConnection);
    }

    @Override
    public List<Breed> getAll() throws PersistentException {
        try {
            connection.setAutoCommit(false);
            BreedDAO dao = (BreedDAO) createDao(DAOEnum.BREED);
            List<Breed> breeds = dao.getAll();
            commit();
            connection.setAutoCommit(true);
            return breeds;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public Breed getByID(Integer ID) throws PersistentException
    {
        try {
            connection.setAutoCommit(false);
            BreedDAO dao = (BreedDAO) createDao(DAOEnum.BREED);
            Breed breed = dao.getByID(ID);
            commit();
            connection.setAutoCommit(true);
            return breed;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }
}
