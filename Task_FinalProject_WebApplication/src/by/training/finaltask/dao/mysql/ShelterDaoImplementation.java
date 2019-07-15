package by.training.finaltask.dao.mysql;

import by.training.finaltask.dao.daointerface.ShelterDAO;
import by.training.finaltask.entity.Breed;
import by.training.finaltask.entity.Shelter;
import by.training.finaltask.exception.PersistentException;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class ShelterDaoImplementation extends BaseDAO implements ShelterDAO {

    public ShelterDaoImplementation(Connection aliveConnection)
    {
        super(aliveConnection);
        this.resourceBundle = ResourceBundle.getBundle(PROPERTY_PATH);
    }


    @Override
    public List<Breed> getAll() throws PersistentException {
        return null;
    }

    @Override
    public Breed getByID(Integer id) throws PersistentException {
        return null;
    }

    @Override
    public Integer add(Shelter element) throws PersistentException {
        return null;
    }

    @Override
    public Shelter get() throws PersistentException {
        return null;
    }

    @Override
    public boolean update(Shelter element) throws PersistentException {
        return false;
    }

    @Override
    public boolean delete(Shelter element) throws PersistentException {
        return false;
    }
}
