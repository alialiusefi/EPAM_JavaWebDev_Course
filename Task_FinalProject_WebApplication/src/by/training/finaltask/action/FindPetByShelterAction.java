package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.PetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class FindPetByShelterAction extends Action {

    private static final Logger LOGGER = LogManager.getLogger(
            FindPetByShelterAction.class);

    private static int ROWS_PER_PAGE = 6;
    private static String NUMBER_REGEX = "[1-9]+";

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            int shelterID = 1;
            /*TODO: continue working on parsing pet status parameter and passing it through findpet.html*/
            String petStatusParam = request.getParameter("petStatus");
            if(request.getParameter("shelter") == null)
            {
                shelterID = Integer.parseInt(request.getParameter("search"));
            } else {
                shelterID = Integer.parseInt(request.getParameter("shelter"));
            }
            Forward forward = new Forward("/pets/findpet.html?page=1");
            forward.getAttributes().put("searchParameter",shelterID);
            PetService service = (PetService) new ServiceFactoryImpl().createService(DAOEnum.PET);
            int amountOfPetsByShelter = service.getAmountOfAllPetsByShelter(shelterID);
            int amountOfPages = amountOfPetsByShelter % ROWS_PER_PAGE == 0 ?
                    amountOfPetsByShelter / ROWS_PER_PAGE : amountOfPetsByShelter / ROWS_PER_PAGE + 1;
            forward.getAttributes().put("amountOfPages", amountOfPages);
            Integer pagenumber = validatePageNumber(
                    request.getParameter("page"), amountOfPages);
            int offset = (pagenumber - 1) * ROWS_PER_PAGE;
            List<Pet> pets = service.getAllByShelter(shelterID,offset,ROWS_PER_PAGE);
            forward.getAttributes().put("petResults",pets);
            List<String> images = getImages(request, pets);
            forward.getAttributes().put("petPictures", images);
            forward.getAttributes().put("paginationURL","/pets/findpetbyshelter.html");
            return forward;
        }
        throw new PersistentException("forbiddenAccess");
    }

    private int validatePageNumber(String pageParameter, int amountOfPages) {
        if (pageParameter.matches(NUMBER_REGEX)) {
            Integer pageNumber = Integer.parseInt(
                    pageParameter);
            if (pageNumber <= amountOfPages) {
                return pageNumber;
            } else {
                return 1;
            }
        }
        return 1;
    }

    private List<String> getImages(HttpServletRequest request, List<Pet> pets)
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
}
