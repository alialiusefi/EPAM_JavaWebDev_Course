package by.training.finaltask.service;

import by.training.finaltask.entity.Shelter;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.ShelterService;

import java.sql.Connection;
import java.util.List;

public class ShelterServiceImpl extends ServiceImpl implements ShelterService {

    public ShelterServiceImpl(Connection aliveConnection)
    {
        super(aliveConnection);
    }

    @Override
    public List<Shelter> getAll() throws PersistentException {
        return null;
    }

    @Override
    public Shelter getByID(Integer ID) throws PersistentException {
        return null;
    }
}
