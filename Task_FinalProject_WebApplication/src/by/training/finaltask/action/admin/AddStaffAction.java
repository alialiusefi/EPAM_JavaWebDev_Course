package by.training.finaltask.action.admin;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.UserInfoFormParser;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.UserInfoService;
import by.training.finaltask.service.serviceinterface.UserService;
import by.training.finaltask.parser.FormParserEnum;
import by.training.finaltask.parser.FormParserFactory;
import by.training.finaltask.parser.UserFormParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddStaffAction extends AuthorizedUserAction {

    private static Logger logger = LogManager.getLogger(AddStaffAction.class);

    private static final FormParserFactory FORM_PARSER_FACTORY = new FormParserFactory();

    public AddStaffAction() {
        allowedRoles.add(Role.ADMINISTRATOR);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response)
            throws PersistentException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("authorizedUser");
            if (user != null && allowedRoles.contains(user.getUserRole())) {
                List<String> userParameters = new ArrayList<>();
                List<String> userInfoParameters = new ArrayList<>();
                addUserParametersToList(request, userParameters);
                addUserInfoParametersToList(request, userInfoParameters);
                UserFormParser userValidator = (UserFormParser) FORM_PARSER_FACTORY.getValidator(
                        FormParserEnum.USERFORM);
                UserInfoFormParser userInfoFormValidator = (UserInfoFormParser) FORM_PARSER_FACTORY.getValidator(
                        FormParserEnum.USERINFOFORM);
                try {
                    User staff = userValidator.parse(userParameters);
                    staff.setUserRole(Role.STAFF);
                    UserService userService = (UserService) new ServiceFactoryImpl()
                            .createService(DAOEnum.USER);
                    UserInfo staffuserInfo = userInfoFormValidator.parse(userInfoParameters);
                    UserInfoService userInfoService = (UserInfoService)
                            new ServiceFactoryImpl().createService(DAOEnum.USERINFO);
                    int userIDGenerated = userService.add(staff);
                    staffuserInfo.setId(userIDGenerated);
                    userInfoService.add(staffuserInfo);
                    Forward forward = new Forward("/user/admin/addstaff.html", true);
                    forward.getAttributes().put("successMessage", "staffAdded");
                    return forward;
                } catch (InvalidFormDataException e) {
                    request.setAttribute("message", e.getMessage());
                    return null;
                }
            }
            return new Forward("/login.html", true);
        }
        return new Forward("/login.html", true);
    }

    private void addUserParametersToList(HttpServletRequest request, List<String> userParameters) {
        userParameters.add(request.getParameter("username"));
        userParameters.add(request.getParameter("password"));
    }

    private void addUserInfoParametersToList(HttpServletRequest request, List<String> userInfoParameters) {
        userInfoParameters.add(request.getParameter("email"));
        userInfoParameters.add(request.getParameter("firstname"));
        userInfoParameters.add(request.getParameter("lastname"));
        userInfoParameters.add(request.getParameter("dateofbirth"));
        userInfoParameters.add(request.getParameter("address"));
        userInfoParameters.add(request.getParameter("contactnumber"));
    }
}
