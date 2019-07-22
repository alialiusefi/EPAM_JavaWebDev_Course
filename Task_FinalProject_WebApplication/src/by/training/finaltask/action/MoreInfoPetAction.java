package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.*;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.BreedService;
import by.training.finaltask.service.serviceinterface.PetService;
import by.training.finaltask.service.serviceinterface.ShelterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Base64;

public class MoreInfoPetAction extends AuthorizedUserAction {

    public static final Logger LOGGER = LogManager.getLogger(
            MoreInfoPetAction.class);

    public MoreInfoPetAction() {
        this.allowedRoles.add(Role.STAFF);
        this.allowedRoles.add(Role.GUEST);
        this.allowedRoles.add(Role.ADMINISTRATOR);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null && allowedRoles.contains(user.getUserRole())) {
                PetService petService = (PetService) new ServiceFactoryImpl().
                        createService(DAOEnum.PET);
                BreedService breedService = (BreedService) new ServiceFactoryImpl().
                        createService(DAOEnum.BREED);
                ShelterService shelterService = (ShelterService) new ServiceFactoryImpl().
                        createService(DAOEnum.SHELTER);
                String petIDParam = request.getParameter("petID");
                if (petIDParam != null) {
                    int petID = Integer.parseInt(petIDParam);
                    Pet pet = petService.get(petID);
                    request.setAttribute("pet", pet);
                    String petPic = getImage(request,pet);
                    request.setAttribute("petPicture",petPic);
                    Breed breed = breedService.getByID(pet.getBreedID());
                    Shelter shelter = shelterService.getByID(pet.getShelterID());
                    request.setAttribute("breed",breed);
                    request.setAttribute("shelter",shelter);
                    return null;
                } else {
                    return new Forward("/pets/findpet.html", true);
                }
            }
            Forward forward = new Forward("/login.html", true);
            forward.getAttributes().put("message", "pleaseLogIn");
            return forward;
        }
        throw new PersistentException("forbiddenAccess");
    }

    private String getImage(HttpServletRequest request, Pet pet)
            throws PersistentException {
        String image;
        byte[] array;
        try {
            array = pet.getPhoto().getBytes(1, (int) pet.getPhoto().length());
            image = Base64.getEncoder().encodeToString(array);
        } catch (SQLException r) {
            LOGGER.warn(r.getMessage(), r);
            throw new PersistentException(r.getMessage(), r);
        }
        return image;
    }
}
