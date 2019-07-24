package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.FormParser;
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

public class FindPetByShelterAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger(
            FindPetByShelterAction.class);

    public FindPetByShelterAction(){
        this.allowedRoles.add(Role.STAFF);
        this.allowedRoles.add(Role.ADMINISTRATOR);
    }

    private static int ROWS_PER_PAGE = 6;
    private static String NUMBER_REGEX = "[1-9]+";
    private static String PETSTATUS_ATTRIBUTE = "petStatus";

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authUser = (User)session.getAttribute("authorizedUser");
            int shelterID = getShelterID(request);
            PetStatus status;
            if(authUser != null && this.allowedRoles.contains(authUser.getUserRole()))
            {
                status = FindPetAction.getStatus(request);
            } else {
                status = PetStatus.SHELTERED;
            }
            Forward forward = new Forward("/pets/findpet.html?page=1");
            forward.getAttributes().put("searchParameter", shelterID);
            session.setAttribute(PETSTATUS_ATTRIBUTE,status);
            PetService service = (PetService) factory.createService(DAOEnum.PET);
            int amountOfPetsByShelter = service.getAmountOfAllPetsByShelter(status,shelterID);
            int amountOfPages = amountOfPetsByShelter % ROWS_PER_PAGE == 0 ?
                    amountOfPetsByShelter / ROWS_PER_PAGE : amountOfPetsByShelter / ROWS_PER_PAGE + 1;
            forward.getAttributes().put("amountOfPages", amountOfPages);
            int pageNumber = FormParser.parsePageNumber(
                    request.getParameter("page"), amountOfPages);
            int offset = (pageNumber - 1) * ROWS_PER_PAGE;
            List<Pet> pets = service.getAllByShelter(status,shelterID, offset, ROWS_PER_PAGE);
            forward.getAttributes().put("petResults", pets);
            List<String> images = getImages(request, pets);
            forward.getAttributes().put("petPictures", images);
            forward.getAttributes().put("paginationURL", "/pets/findpetbyshelter.html");
            return forward;
        }
        throw new PersistentException("forbiddenAccess");
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

    private int getShelterID(HttpServletRequest request)
    {
        if (request.getParameter("shelter") == null) {
            return Integer.parseInt(request.getParameter("search"));
        } else {
            return Integer.parseInt(request.getParameter("shelter"));
        }
    }
}
