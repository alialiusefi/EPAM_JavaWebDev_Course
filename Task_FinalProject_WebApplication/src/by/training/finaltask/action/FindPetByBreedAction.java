package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.FormParser;
import by.training.finaltask.service.serviceinterface.PetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindPetByBreedAction extends AuthorizedUserAction {
    private static final Logger LOGGER = LogManager.getLogger(
            FindPetByBreedAction.class);

    public FindPetByBreedAction() {
        this.allowedRoles.add(Role.STAFF);
        this.allowedRoles.add(Role.ADMINISTRATOR);
    }

    private static int ROWS_PER_PAGE = 6;
    private static String PETSTATUS_ATTRIBUTE = "petStatus";

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authUser = (User) session.getAttribute("authorizedUser");
            int breedID = getBreedID(request);
            PetStatus status;
            if (authUser != null && this.allowedRoles.contains(authUser.getUserRole())) {
                status = FindPetAction.getStatus(request);
            } else {
                status = PetStatus.SHELTERED;
            }
            Forward forward = new Forward("/pets/findpet.html?page=1");
            forward.getAttributes().put("searchParameter", breedID);
            session.setAttribute(PETSTATUS_ATTRIBUTE, status);
            PetService service = (PetService) factory.createService(DAOEnum.PET);
            int amountOfPetsByBreed = service.getAmountOfAllPetsByBreed(status, breedID);
            int amountOfPages = amountOfPetsByBreed % ROWS_PER_PAGE == 0 ?
                    amountOfPetsByBreed / ROWS_PER_PAGE : amountOfPetsByBreed / ROWS_PER_PAGE + 1;
            forward.getAttributes().put("amountOfPages", amountOfPages);
            int pagenumber = FormParser.parsePageNumber(
                    request.getParameter("page"), amountOfPages);
            int offset = (pagenumber - 1) * ROWS_PER_PAGE;
            List<Pet> pets = service.getAllByBreed(status, breedID, offset, ROWS_PER_PAGE);
            forward.getAttributes().put("petResults", pets);
            List<String> images = FindPetAction.getImages(request, pets);
            forward.getAttributes().put("petPictures", images);
            forward.getAttributes().put("paginationURL", "/pets/findpetbybreed.html");
            return forward;
        }
        throw new PersistentException("forbiddenAccess");
    }

    private int getBreedID(HttpServletRequest request) {
        if (request.getParameter("breed") == null) {
            return Integer.parseInt(request.getParameter("search"));
        } else {
            return Integer.parseInt(request.getParameter("breed"));
        }
    }
}
