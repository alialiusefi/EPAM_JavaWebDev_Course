package by.training.finaltask.action.guest;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Adoption;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.AdoptionFormParser;
import by.training.finaltask.parser.FormParser;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.AdoptionService;
import by.training.finaltask.service.serviceinterface.PetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.security.krb5.internal.PAEncTSEnc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AdoptPetAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger(AdoptPetAction.class);
    private static final AdoptionFormParser formParser = new AdoptionFormParser();
    private static final String MESSAGEATTRIBUTE = "message";
    private static final String PETID = "petID";
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
                PetService petService = (PetService) new ServiceFactoryImpl()
                        .createService(DAOEnum.PET);
                String petIDParam = request.getParameter(PETID);
                if(petIDParam == null)
                {
                    petIDParam = (String)request.getAttribute(PETID);
                }
                Pet pet = petService.get(Integer.parseInt(petIDParam));
                request.setAttribute(PETID,pet.getId());
                request.setAttribute("pet",pet);
                request.setAttribute("petPicture",getImage(request,pet));
                List<String> adoptionParameters = new ArrayList<>();
                addAdoptionParametersToList(request,adoptionParameters);
                try {
                    Adoption adoption = formParser.parse(this,adoptionParameters);
                    AdoptionService adoptionService = (AdoptionService)
                            new ServiceFactoryImpl().createService(DAOEnum.ADOPTION);
                    adoptionService.add(adoption);
                    Forward forward = new Forward("/adoptions/myadoptions.html",true);
                    forward.getAttributes().put("successMessage","petAdoptedSuccessfully");
                    return null;
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
        parameters.add(request.getParameter(PETID));
        parameters.add(request.getParameter("adoptionStart"));
        String adoptionEndDate = request.getParameter("adoptionEnd");
        if(adoptionEndDate == null)
        {
            parameters.add("INDEFINITE");
        } else {
            parameters.add(adoptionEndDate);
        }
        User authUser = (User)request.getSession(false).getAttribute("authorizedUser");
        int userID = authUser.getId();
        parameters.add(String.valueOf(userID));
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
