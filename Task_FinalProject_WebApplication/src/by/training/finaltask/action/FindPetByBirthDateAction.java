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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class FindPetByBirthDateAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger(
            FindPetByBirthDateAction.class);

    public FindPetByBirthDateAction() {
        this.allowedRoles.add(Role.STAFF);
        this.allowedRoles.add(Role.ADMINISTRATOR);
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static int ROWSPERPAGE = 6;
    private static String PETSTATUS_ATTRIBUTE = "petStatus";
    private static String BIRTHDATE_ATTRIBUTE = "birthDate";
    private static String RELATION_ATTRIBUTE = "birthDateChoice";

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authUser = (User) session.getAttribute("authorizedUser");
            GregorianCalendar gregorianCalendar = getBirthDate(request);
            int relation = getRelation(request);
            PetStatus status;
            if (authUser != null && this.allowedRoles.contains(authUser.getUserRole())) {
                status = FindPetAction.getStatus(request);
            } else {
                status = PetStatus.SHELTERED;
            }
            Forward forward = new Forward("/pets/findpet.html?page=1");
            session.setAttribute(BIRTHDATE_ATTRIBUTE,gregorianCalendar);
            session.setAttribute(PETSTATUS_ATTRIBUTE,status);
            session.setAttribute(RELATION_ATTRIBUTE,relation);
            PetService service = (PetService) factory.createService(DAOEnum.PET);
            int amountOfPetsByBirthDate = service.getAmountOfAllPetsByBirthDate(relation,
                    status,gregorianCalendar);
            int amountOfPages = amountOfPetsByBirthDate % ROWSPERPAGE == 0 ?
                    amountOfPetsByBirthDate / ROWSPERPAGE : amountOfPetsByBirthDate / ROWSPERPAGE + 1;
            forward.getAttributes().put("amountOfPages", amountOfPages);
            int pageNumber = FormParser.parsePageNumber(
                    request.getParameter("page"), amountOfPages);
            int offset = (pageNumber - 1) * ROWSPERPAGE;
            List<Pet> pets = service.getAllByBirthDate(relation,status,
                    gregorianCalendar, offset, ROWSPERPAGE);
            forward.getAttributes().put("petResults", pets);
            List<String> images = FindPetAction.getImages(request, pets);
            forward.getAttributes().put("petPictures", images);
            forward.getAttributes().put("paginationURL", "/pets/findpetbybirthdate.html");
            return forward;
        }
        throw new PersistentException("forbiddenAccess");
    }

    private int getRelation(HttpServletRequest request) {
        String relationStr = request.getParameter(RELATION_ATTRIBUTE);
        if(relationStr == null)
        {
            return (Integer) request.getSession(false).getAttribute(RELATION_ATTRIBUTE);
        }
        if(relationStr.equals("lessthan"))
        {
            return -1;
        } else {
            if(relationStr.equals("greaterthan"))
            {
                return 1;
            }
        }
        return 1;
    }

    private GregorianCalendar getBirthDate(HttpServletRequest request) {
        String dateStr = request.getParameter(BIRTHDATE_ATTRIBUTE);
        if (dateStr == null) {
            return (GregorianCalendar)
                    request.getSession(false).getAttribute(BIRTHDATE_ATTRIBUTE);
        }
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            date = new Date(2012, 2, 1);
        }
        GregorianCalendar dateofbirthgreg = new GregorianCalendar();
        dateofbirthgreg.setTime(date);
        return dateofbirthgreg;
    }


}
