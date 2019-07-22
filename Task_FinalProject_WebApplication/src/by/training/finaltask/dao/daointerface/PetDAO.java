package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface PetDAO extends DAO<Pet> {

    Pet get(int ID) throws PersistentException;
    List<Pet> getAll(int offset, int rowcount) throws PersistentException;
    List<Pet> getAllSheltered(int offset, int rowcount)
            throws PersistentException;
    List<Pet> getAllByShelter(PetStatus status,int shelterID, int offset, int rowcount)
            throws PersistentException;
    List<Pet> getAllByBreed(PetStatus status, int breedID, int offset, int rowcount)
            throws PersistentException;

    boolean delete(Integer ID) throws PersistentException;

    int getAmountOfAllPets() throws PersistentException;
    int getAmountOfAllShelteredPets() throws PersistentException;
    int getAmountOfAllPetsByShelter(int shelterID) throws PersistentException;
    int getAmountOfAllPetsByBreed(int breedID) throws PersistentException;

}
