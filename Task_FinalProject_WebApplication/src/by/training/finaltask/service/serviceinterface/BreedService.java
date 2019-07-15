package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.Breed;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface BreedService extends Service {

    List<Breed> getAll() throws PersistentException;

    Breed getByID(Integer ID) throws PersistentException;

}
