package by.training.finaltask.action.guest;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.FormParser;
import by.training.finaltask.service.serviceinterface.AdoptionService;
import by.training.finaltask.service.serviceinterface.PetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdoptionsAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger(MyAdoptionsAction.class);
    private static final int ROWCOUNT = 10;
    public MyAdoptionsAction() {
        this.allowedRoles.add(Role.GUEST);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            User authUser = (User) request.getAttribute("authorizedUser");
            if(authUser != null && allowedRoles.contains(authUser.getUserRole()))
            {
                List<Adoption> adoptions = (List<Adoption>) request.getAttribute("adoptionResults");
                if(adoptions == null)
                {
                    AdoptionService service = (AdoptionService) factory
                            .createService(DAOEnum.ADOPTION);
                    int amountOfAllAdoptions = service.getAllCount();
                    int amountOfPages = amountOfAllAdoptions % ROWCOUNT == 0 ?
                            amountOfAllAdoptions / ROWCOUNT : amountOfAllAdoptions / ROWCOUNT + 1;
                    request.setAttribute("amountOfPages", amountOfPages);
                    int pageNumber = FormParser.parsePageNumber(
                            request.getParameter("page"), amountOfPages);
                    int offset = (pageNumber - 1) * ROWCOUNT;
                    adoptions = service.getAll(offset, ROWCOUNT);
                    request.setAttribute("paginationURL", "/adoptions/staff/findadoption.html");
                }
                List<Pet> pets = getPetForEveryAdoption(adoptions);
                request.setAttribute("adoptionResults", adoptions);
                request.setAttribute("petResults",pets);
                return null;

            }
        }
        throw new PersistentException("forbiddenAccess");
    }

    private List<Pet> getPetForEveryAdoption(List<Adoption> adoptions)
            throws PersistentException {
        List<Pet> pets = new ArrayList<>();
        Map<Integer, Pet> petMap = new HashMap<>();
        for (Adoption adoption : adoptions) {
            if (!petMap.containsKey(adoption.getPetID())) {
                PetService service = (PetService) factory.createService(DAOEnum.PET);
                Pet pet = service.get(adoption.getPetID());
                pets.add(pet);
                petMap.put(pet.getId(),pet);
                continue;
            }
            pets.add(petMap.get(adoption.getPetID()));
        }
        return pets;
    }

}
