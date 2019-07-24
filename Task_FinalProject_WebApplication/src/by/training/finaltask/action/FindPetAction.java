package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.*;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.FormParser;
import by.training.finaltask.service.serviceinterface.BreedService;
import by.training.finaltask.service.serviceinterface.PetService;
import by.training.finaltask.service.serviceinterface.ShelterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FindPetAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger(FindPetAction.class);
    private static int ROWCOUNT = 6;
    private static String NUMBER_REGEX = "[1-9]+";
    private static String PETSTATUS_ATTRIBUTE = "petStatus";

    public FindPetAction() {
        this.allowedRoles.add(Role.STAFF);
        this.allowedRoles.add(Role.ADMINISTRATOR);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            PetService petService = (PetService) factory
                    .createService(DAOEnum.PET);
            updateSelectionList(request);
            @SuppressWarnings("unchecked")
            List<Pet> pets = (List<Pet>) request.getAttribute("petResults");
            @SuppressWarnings("unchecked")
            List<String> petPicturePaths = (List<String>) request.getAttribute(
                    "petPicturePaths");
            if (pets == null && petPicturePaths == null) {
                int amountOfAllPets;
                if (user != null && allowedRoles.contains(user.getUserRole())) {
                    amountOfAllPets = petService.getAmountOfAllPets();
                } else {
                    amountOfAllPets = petService.getAmountOfAllShelteredPets();
                }
                int amountOfPages = amountOfAllPets % ROWCOUNT == 0 ?
                        amountOfAllPets / ROWCOUNT : amountOfAllPets / ROWCOUNT + 1;
                request.setAttribute("amountOfPages", amountOfPages);
                int pagenumber = 1;
                pagenumber = FormParser.parsePageNumber(
                        request.getParameter("page"), amountOfPages);
                int offset = (pagenumber - 1) * ROWCOUNT;
                if (user != null && allowedRoles.contains(user.getUserRole())) {
                    pets = petService.getAll(offset, ROWCOUNT);
                } else {
                    pets = petService.getAllSheltered(offset, ROWCOUNT);
                }
                request.setAttribute("petResults", pets);
                List<String> images = getImages(request, pets);
                request.setAttribute("petPictures", images);
                request.setAttribute("paginationURL", "/pets/findpet.html");

            }
            return null;
        }
        throw new PersistentException("forbiddenAccess");
    }


    static List<String> getImages(HttpServletRequest request, List<Pet> pets)
            throws PersistentException {
        List<String> images = new ArrayList<>();
        for (Pet pet : pets) {
            byte[] array;
            try {
                array = pet.getPhoto().getBytes(1, (int) pet.getPhoto().length());
                String encode = Base64.getEncoder().encodeToString(array);
                images.add(encode);
            } catch (SQLException r) {
                LOGGER.warn(r.getMessage(), r);
                throw new PersistentException(r.getMessage(), r);
            }
        }
        return images;
    }

    private void updateSelectionList(HttpServletRequest request)
            throws PersistentException {
        BreedService breedService = (BreedService)
                factory.createService(DAOEnum.BREED);
        ShelterService shelterService = (ShelterService)
                factory.createService(DAOEnum.SHELTER);
        List<Breed> breeds = breedService.getAll();
        List<Shelter> shelters = shelterService.getAll();
        request.setAttribute("shelterList", shelters);
        request.setAttribute("breedList", breeds);
    }

    static PetStatus getStatus(HttpServletRequest request) {
        String petStatusParam = request.getParameter(PETSTATUS_ATTRIBUTE);
        if (petStatusParam == null) {
            return (PetStatus) request.getSession(false)
                    .getAttribute(PETSTATUS_ATTRIBUTE);
        }
        if (petStatusParam.equals("ALL")) {
            return null;
        }
        return PetStatus.valueOf(petStatusParam);
    }

}
