package by.training.finaltask.action.staff;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.validator.FormValidatorEnum;
import by.training.finaltask.validator.FormValidatorFactory;
import by.training.finaltask.validator.PetFormValidator;
import com.sun.deploy.net.HttpRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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
import java.util.LinkedList;
import java.util.List;

public class AddPetAction extends AuthorizedUserAction {

    public static final Logger logger = LogManager.getLogger(
            AddPetAction.class);

    private static final FormValidatorFactory formValidatorFactory = new FormValidatorFactory();

    public AddPetAction() {
        this.allowedRoles.add(Role.STAFF);
    }

    @Override
    public Forward exec(HttpServletRequest request,
                        HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User authUser = (User)session.getAttribute("authorizedUser");
            if (authUser != null &&
                    this.allowedRoles.contains(authUser.getUserRole())) {
                List<String> petParameters = new ArrayList<>();
                try {
                    addPetParametersToList(request,petParameters);
                    if (petParameters.stream().anyMatch(e -> e != null)){
                        PetFormValidator validator = (PetFormValidator)
                                formValidatorFactory.getValidator(FormValidatorEnum.PETFORM);
                        Pet pet;
                        try {
                            pet = validator.validate(petParameters);
                        } catch (InvalidFormDataException e) {
                            throw new PersistentException(e.getMessage(),e);
                        }
                        System.out.println(pet);
                    }
                    else {
                        request.setAttribute("message","fillAllFields");
                        return null;
                    }
                } catch (FileUploadException e) {
                    throw new PersistentException("fileUploadError");
                }
            }
        }
        throw new PersistentException("forbiddenAccess");
    }

    private void addPetParametersToList(HttpServletRequest request,
                                        List<Object> petParam)
            throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute(
                "javax.servlet.context.tmpdir");
        factory.setRepository(repository);

        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = upload.parseRequest(request);
        FileItem item = items.iterator().next();
        File file = new File("/petpics/"
                + request.getParameter("petName") + ".jpg");
        try {
            item.write(file);
        } catch (Exception e) {
            throw new FileUploadException("fileUploadError");
        }
        petParam.add(file.getPath());
        petParam.add(request.getParameter("petName"));
        petParam.add(request.getParameter("petWeight"));
        petParam.add(request.getParameter("dateOfBirth"));
        petParam.add(request.getParameter("dateSheltered"));
        petParam.add(request.getParameter("shelter"));
        petParam.add(request.getParameter("breed"));
        petParam.add(request.getParameter("petStatus"));
        System.out.println("Reached Here!");
    }
}
