package by.training.finaltask.service;

import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.AdoptionService;

import java.sql.Connection;
import java.util.List;

public class AdoptionServiceImpl extends ServiceImpl implements AdoptionService {

    public AdoptionServiceImpl (Connection aliveConnection)
    {
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
    public Integer add(Adoption adoption) throws PersistentException {
        return null;
    }

    @Override
    public void update(Adoption adoption) throws PersistentException {

    }

    @Override
    public void delete(int ID) throws PersistentException {

    }
}
