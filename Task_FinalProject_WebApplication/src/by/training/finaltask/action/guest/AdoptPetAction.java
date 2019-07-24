package by.training.finaltask.action.guest;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.AdoptionFormParser;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.AdoptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AdoptPetAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger(AdoptPetAction.class);
    private static final AdoptionFormParser formParser = new AdoptionFormParser();
    private static final String MESSAGEATTRIBUTE = "message";
    public AdoptPetAction(){
        this.allowedRoles.add(Role.GUEST);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            User authUser = (User)session.getAttribute("authorizedUser");
            if(authUser != null && this.allowedRoles.contains(authUser.getUserRole()))
            {
                List<String> adoptionParameters = new ArrayList<>();
                addAdoptionParametersToList(request,adoptionParameters);
                try {
                    Adoption adoption = formParser.parse(this,adoptionParameters);
                    AdoptionService adoptionService = (AdoptionService)
                            new ServiceFactoryImpl().createService(DAOEnum.ADOPTION);
                    adoptionService.add(adoption);
                    Forward forward = new Forward("/adoptions/myadoptions.html",true);
                    forward.getAttributes().put("successMessage","petAdoptedSuccessfully");
                } catch (InvalidFormDataException e)
                {
                    request.setAttribute(MESSAGEATTRIBUTE,e.getMessage());
                    return null;
                }
            }
            Forward forward = new Forward("/pets/findpet.html?page=1",true);
            forward.getAttributes().put(MESSAGEATTRIBUTE,"loginAsGuest");
            return forward;
        } throw new PersistentException("forbiddenAccess");
    }

    private void addAdoptionParametersToList(HttpServletRequest request, List<String> parameters)
    {
        parameters.add(request.getParameter("petID"));
        parameters.add(request.getParameter("adoptionStart"));
        parameters.add(request.getParameter("adoptionEnd"));
        parameters.add(request.getParameter("userID"));
    }

}
