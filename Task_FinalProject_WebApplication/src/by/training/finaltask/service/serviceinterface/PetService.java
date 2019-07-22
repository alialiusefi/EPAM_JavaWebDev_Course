package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.Pet;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface PetService extends Service {

    Pet get(int ID) throws PersistentException;

    List<Pet> getAllSheltered(int offset, int rowcount) throws PersistentException;

    List<Pet> getAll(int offset, int rowcount) throws PersistentException;

    int getAmountOfAllPets() throws PersistentException;

    int getAmountOfAllShelteredPets() throws PersistentException;

    Integer add(Pet pet) throws PersistentException;

    void update(Pet pet) throws PersistentException;

    void delete(int ID) throws PersistentException;
}
