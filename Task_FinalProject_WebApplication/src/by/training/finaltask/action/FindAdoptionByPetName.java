package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.FormParser;
import by.training.finaltask.service.serviceinterface.AdoptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class FindAdoptionByPetName extends AuthorizedUserAction {

    private static final Logger LOGGER =  LogManager.getLogger(FindAdoptionByPetName.class);
    private static final int ROWCOUNT = 10;

    public FindAdoptionByPetName() {
        this.allowedRoles.add(Role.STAFF);
        this.allowedRoles.add(Role.GUEST);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authUser = (User) session.getAttribute("authorizedUser");
            if (authUser != null && allowedRoles.contains(authUser.getUserRole())) {
                String petName = request.getParameter("petName");
                if (petName == null) {
                    petName = (String) session.getAttribute("petName");
                } else {
                    session.setAttribute("petName", petName);
                }
                AdoptionService service = (AdoptionService) factory.createService(
                        DAOEnum.ADOPTION);
                Forward forward = new Forward(request.getHeader("referer"));
                List<Adoption> adoptions = new ArrayList<>();
                if (authUser.getUserRole() == Role.GUEST) {
                    int amountOfAllAdoptions = service.getCountPetNameCurrentUser(
                            authUser.getId(), "%" + petName + "%");
                    int amountOfPages = amountOfAllAdoptions % ROWCOUNT == 0 ?
                            amountOfAllAdoptions / ROWCOUNT :
                            amountOfAllAdoptions / ROWCOUNT + 1;
                    forward.getAttributes().put("amountOfPages", amountOfPages);
                    int pageNumber = FormParser.parsePageNumber(
                            request.getParameter("page"), amountOfPages);
                    int offset = (pageNumber - 1) * ROWCOUNT;
                    adoptions = service.getAllPetNameCurrentUser(authUser.getId(), petName,
                            offset, ROWCOUNT);
                }
                if (authUser.getUserRole() == Role.STAFF) {
                    int amountOfAllAdoptions = service.getCountPetName(
                            "%" + petName + "%");
                    int amountOfPages = amountOfAllAdoptions % ROWCOUNT == 0 ?
                            amountOfAllAdoptions / ROWCOUNT :
                            amountOfAllAdoptions / ROWCOUNT + 1;
                    forward.getAttributes().put("amountOfPages", amountOfPages);
                    int pageNumber = FormParser.parsePageNumber(
                            request.getParameter("page"), amountOfPages);
                    int offset = (pageNumber - 1) * ROWCOUNT;
                    adoptions = service.getAllPetName("%" + petName + "%",
                            offset, ROWCOUNT);
                }
                forward.getAttributes().put("paginationURL", "/adoptions/staff/findadoptionbypetname.html");
                forward.getAttributes().put("adoptionResults", adoptions);
                return forward;
            }
        }
        throw new PersistentException("forbiddenAccess");
    }

}
