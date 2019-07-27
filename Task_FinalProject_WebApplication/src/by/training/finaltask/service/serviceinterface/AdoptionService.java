package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface AdoptionService extends Service{

    Adoption get(int ID) throws PersistentException;

    List<Adoption> getAll(int offset, int rowcount) throws PersistentException;

    int getAllCount() throws PersistentException;

    Integer add(Adoption adoption) throws PersistentException, InvalidFormDataException;

    void update(Adoption adoption) throws PersistentException, InvalidFormDataException;

    void delete(int adoptionID) throws PersistentException, InvalidFormDataException;

}
