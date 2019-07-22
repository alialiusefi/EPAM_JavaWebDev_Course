package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Pet;
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

public class FindPetByBreedAction extends Action {
    private static final Logger LOGGER = LogManager.getLogger(
            FindPetByBreedAction.class);

    private static int ROWS_PER_PAGE = 6;
    private static String NUMBER_REGEX = "[1-9]+";

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            int breedID = 1;
            if(request.getParameter("breed") == null)
            {
                breedID = Integer.parseInt(request.getParameter("search"));
            } else {
                breedID = Integer.parseInt(request.getParameter("breed"));
            }
            Forward forward = new Forward("/pets/findpet.html?page=1");
            forward.getAttributes().put("searchParameter",breedID);
            PetService service = (PetService) new ServiceFactoryImpl().createService(DAOEnum.PET);
            int amountOfPetsByBreed = service.getAmountOfAllPetsByBreed(breedID);
            int amountOfPages = amountOfPetsByBreed % ROWS_PER_PAGE == 0 ?
                    amountOfPetsByBreed / ROWS_PER_PAGE : amountOfPetsByBreed / ROWS_PER_PAGE + 1;
            forward.getAttributes().put("amountOfPages", amountOfPages);
            Integer pagenumber = validatePageNumber(
                    request.getParameter("page"), amountOfPages);
            int offset = (pagenumber - 1) * ROWS_PER_PAGE;
            List<Pet> pets = service.getAllByBreed(breedID,offset,ROWS_PER_PAGE);
            forward.getAttributes().put("petResults",pets);
            List<String> images = getImages(request, pets);
            forward.getAttributes().put("petPictures", images);
            forward.getAttributes().put("paginationURL","/pets/findpetbybreed.html");
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
