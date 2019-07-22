package by.training.finaltask.service;

import by.training.finaltask.dao.daointerface.ShelterDAO;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Shelter;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.ShelterService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShelterServiceImpl extends ServiceImpl implements ShelterService {

    public ShelterServiceImpl(Connection aliveConnection)
    {
        super(aliveConnection);
    }

    @Override
    public List<Shelter> getAll() throws PersistentException {
        try {
            connection.setAutoCommit(false);
            ShelterDAO dao = (ShelterDAO) createDao(DAOEnum.SHELTER);
            List<Shelter> shelters = dao.getAll();
            commit();
            connection.setAutoCommit(true);
            return shelters;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public Shelter getByID(Integer id) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            ShelterDAO dao = (ShelterDAO) createDao(DAOEnum.SHELTER);
            Shelter shelter = dao.getByID(id);
            commit();
            connection.setAutoCommit(true);
            return shelter;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }
}
