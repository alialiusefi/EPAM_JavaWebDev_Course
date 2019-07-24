package by.training.finaltask.dao.daointerface;

import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.exception.PersistentException;

import java.util.GregorianCalendar;
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
    List<Pet> getAllByBirthDate(int relation, PetStatus status, GregorianCalendar calendar,
                                int offset, int rowcount) throws PersistentException;
    boolean delete(Integer ID) throws PersistentException;

    int getAmountOfAllPetsByBirthDate(int relation, PetStatus status, GregorianCalendar calendar)
            throws PersistentException;
    int getAmountOfAllPets() throws PersistentException;
    int getAmountOfAllShelteredPets() throws PersistentException;
    int getAmountOfAllPetsByShelter(PetStatus status,int shelterID) throws PersistentException;
    int getAmountOfAllPetsByBreed(PetStatus status, int breedID) throws PersistentException;

}
