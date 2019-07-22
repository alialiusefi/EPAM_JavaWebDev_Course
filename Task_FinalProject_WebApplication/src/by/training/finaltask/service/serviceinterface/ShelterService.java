package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.Shelter;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface ShelterService extends Service {

    List<Shelter> getAll() throws PersistentException;

    Shelter getByID(Integer ID) throws PersistentException;


}
