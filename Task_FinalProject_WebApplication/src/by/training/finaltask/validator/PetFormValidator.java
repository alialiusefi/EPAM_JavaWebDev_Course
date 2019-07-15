package by.training.finaltask.validator;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Breed;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.Shelter;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.BreedService;

import java.util.List;

public class PetFormValidator implements FormValidator<Pet> {

    private static final int PICTURE_PATH = 0;
    private static final int PET_NAME = 1;
    private static final int PET_WEIGHT = 2;
    private static final int DATE_OF_BIRTH = 3;
    private static final int DATE_SHELTERED = 4;
    private static final int SHELTER = 5;
    private static final int BREED = 6;
    private static final int PET_STATUS = 7;
    private static final String PET_NAME_REGEX = "^[a-zA-Z]+$";
    /*TODO: Find a link regex*/
    private static final String PHOTO_URL_REGEX = "";
    private static final String DATE_REGEX = "";
    private static final String WEIGHT_REGEX = "";

    @Override
    public Pet validate(List<String> parameters) throws
            InvalidFormDataException, PersistentException {
        String picturePath = parameters.get(PICTURE_PATH);
        if (picturePath.matches(PHOTO_URL_REGEX)) {
            String petName = parameters.get(PET_NAME);
            if (petName.matches(PET_NAME_REGEX)) {
                String petWeight = parameters.get(PET_WEIGHT);
                if (petWeight.matches(WEIGHT_REGEX)) {
                    String dateOfBirth = parameters.get(DATE_OF_BIRTH);
                    if (dateOfBirth.matches(DATE_REGEX)) {
                        String dateSheltered = parameters.get(DATE_SHELTERED);
                        if (dateSheltered.matches(DATE_REGEX)) {
                            Shelter shelter = validateShelter(
                                    parameters.get(SHELTER));
                            Breed breed = validateBreed(parameters.get(BREED));
                            String status = parameters.get(PET_STATUS);
                            return new Pet(0, petName, picturePath, validateBreed());
                        }
                        throw new InvalidFormDataException("incorrectDateFormat");
                    }
                    throw new InvalidFormDataException("incorrectDateFormat");
                }
                throw new InvalidFormDataException("incorrectWeightFormat");
            }
            throw new InvalidFormDataException("incorrectPetNameFormat");
        }
        throw new InvalidFormDataException("uploadPicture");
    }

    private Shelter validateShelter(String shelterID) throws
            InvalidFormDataException, PersistentException {
        ShelterService shelterService = (ShelterService)
                new ServiceFactoryImpl().createService(DAOEnum.BREED);
        int id = Integer.parseInt(shelterID);
        Shelter shelter = shelterService.getByID(id);
        if (shelter != null) {
            return shelter;
        } else {
            throw new InvalidFormDataException("incorrectShelter");
        }
    }

    private Breed validateBreed(String breedID) throws InvalidFormDataException, PersistentException {
        BreedService breedService = (BreedService) new ServiceFactoryImpl()
                .createService(DAOEnum.BREED);
        int id = Integer.parseInt(breedID);
        Breed breed = breedService.getByID(id);
        if (breed != null) {
            return breed;
        }
        throw new InvalidFormDataException("incorrectBreed");

    }
}
