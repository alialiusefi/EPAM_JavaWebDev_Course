package by.training.finaltask.action.staff;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.*;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.PetFormParser;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.BreedService;
import by.training.finaltask.service.serviceinterface.PetService;
import by.training.finaltask.service.serviceinterface.ShelterService;
import by.training.finaltask.parser.FormParserEnum;
import by.training.finaltask.parser.FormParserFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddPetAction extends AuthorizedUserAction {

    public static final Logger LOGGER = LogManager.getLogger(
            AddPetAction.class);

    private static final FormParserFactory FORM_PARSER_FACTORY = new FormParserFactory();
    private static final String UPLOAD_PATH = "C:" + File.separator + "SERVERS"
            + File.separator + "apache-tomcat" + File.separator + "webapps" + File.separator
            + "Pet Shelter" + File.separator + "petpics";
    private static final String MESSAGE_ATTRIBUTE = "message";

    public AddPetAction() {
        this.allowedRoles.add(Role.STAFF);
    }

    @Override
    public Forward exec(HttpServletRequest request,
                        HttpServletResponse response)
            throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authUser = (User) session.getAttribute("authorizedUser");
            if (authUser != null &&
                    this.allowedRoles.contains(authUser.getUserRole())) {
                List<String> petParameters = new ArrayList<>();
                updateSelectionList(request);
                addPetParametersToList(request, petParameters);
                PetFormParser validator = (PetFormParser)
                        FORM_PARSER_FACTORY.getValidator(
                                FormParserEnum.PETFORM);
                Pet pet;
                try {
                    pet = validator.parse(petParameters);
                    PetService petService = (PetService)
                            new ServiceFactoryImpl().createService(DAOEnum.PET);
                    petService.add(pet);
                    File fileToDelete = new File(UPLOAD_PATH +
                            File.separator + request.getSession(false).getId() + ".jpg");
                    if (fileToDelete.delete()) {
                        Forward forward = new Forward("/pets/staff/addpet.html", true);
                        forward.getAttributes().put("successMessage", "petAddedSuccesfully");
                        return forward;
                    } else {
                        LOGGER.warn("Cannot delete file " + fileToDelete);
                        return new Forward("/pets/staff/addpet.html", true);
                    }
                } catch (InvalidFormDataException e) {
                    request.setAttribute(MESSAGE_ATTRIBUTE, e.getMessage());
                    return null;
                }
            }
        }
        throw new PersistentException("forbiddenAccess");
    }

    private void addPetParametersToList(HttpServletRequest request,
                                        List<String> petParam) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute(
                ServletContext.TEMPDIR);
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        File uploadDir = new File(UPLOAD_PATH);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        File storeFile = null;
        try {
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && !formItems.isEmpty()) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = request.getSession(false).getId();
                        String filePath = UPLOAD_PATH + File.separator + fileName + ".jpg";
                        storeFile = new File(filePath);
                        item.write(storeFile);
                        petParam.add(storeFile.getAbsolutePath());

                    } else {
                        String param = item.getString("UTF-8");
                        petParam.add(param);
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute(MESSAGE_ATTRIBUTE,
                    "fillAllFields");
        }

    }

    private void updateSelectionList(HttpServletRequest request)
            throws PersistentException {
        BreedService breedService = (BreedService)
                new ServiceFactoryImpl().createService(DAOEnum.BREED);
        ShelterService shelterService = (ShelterService)
                new ServiceFactoryImpl().createService(DAOEnum.SHELTER);
        List<Breed> breeds = breedService.getAll();
        List<Shelter> shelters = shelterService.getAll();
        request.setAttribute("shelterList", shelters);
        request.setAttribute("breedList", breeds);
    }
}
