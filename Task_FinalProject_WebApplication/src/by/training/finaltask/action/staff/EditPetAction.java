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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class EditPetAction extends AuthorizedUserAction {

    private static final Logger LOGGER = LogManager.getLogger(EditPetAction.class);
    private static final String UPLOAD_PATH = "C:" + File.separator + "SERVERS"
            + File.separator + "apache-tomcat" + File.separator + "webapps" + File.separator
            + "Pet Shelter" + File.separator + "petpics";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String PETID_PARAMETER = "petID";
    public EditPetAction() {
        this.allowedRoles.add(Role.STAFF);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            User authUser = (User)session.getAttribute("authorizedUser");
            if(authUser != null && this.allowedRoles.contains(authUser.getUserRole()))
            {
                PetService service = (PetService) new ServiceFactoryImpl().createService(DAOEnum.PET);
                String petIDParam = request.getParameter(PETID_PARAMETER);
                int petID = Integer.parseInt(petIDParam);
                Pet pet = service.get(petID);
                request.setAttribute("pet",pet);
                updateSelectionList(request);
                String petPic = getImage(pet);
                request.setAttribute("currentPetPicture",petPic);
                List<String> petParameters = new ArrayList<>();
                addPetParametersToList(request,petParameters);
                PetFormParser validator = (PetFormParser)
                        new FormParserFactory().getValidator(FormParserEnum.PETFORM);
                try{
                    Pet newPet = validator.parse(petParameters);
                    newPet.setId(petID);
                    if(newPet.getPhoto() == null)
                    {
                        newPet.setPhoto(pet.getPhoto());
                    }
                    service.update(newPet);
                    File fileToDelete = new File(UPLOAD_PATH +
                            File.separator + request.getSession(false).getId() + ".jpg");
                    if (fileToDelete.delete()) {
                        Forward forward = new Forward("/pets/staff/editpet.html?petID=" + petID, true);
                        forward.getAttributes().put("successMessage", "petUpdatedSuccessfully");
                        return forward;
                    } else {
                        LOGGER.warn("Cannot delete file " + fileToDelete);
                        return new Forward("/pets/staff/editpet.html", true);
                    }
                } catch (InvalidFormDataException e) {
                    request.setAttribute(MESSAGE_ATTRIBUTE, e.getMessage());
                    return null;
                }
            }
            return null;
        }
        throw new PersistentException("forbiddenAccess");
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
    private String getImage(Pet pet)
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
                    if (item.isFormField()) {
                        String param = item.getString("UTF-8");
                        petParam.add(param);
                    } else {
                        String fileName = request.getSession(false).getId();
                        String filePath = UPLOAD_PATH + File.separator + fileName + ".jpg";
                        storeFile = new File(filePath);
                        item.write(storeFile);
                        petParam.add(storeFile.getAbsolutePath());
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute(MESSAGE_ATTRIBUTE,
                    "fillAllFields");
        }

    }


}
