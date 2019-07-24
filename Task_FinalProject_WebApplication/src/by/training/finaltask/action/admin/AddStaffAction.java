package by.training.finaltask.action.admin;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.UserInfoFormParser;
import by.training.finaltask.service.serviceinterface.UserInfoService;
import by.training.finaltask.service.serviceinterface.UserService;
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

    private static final UserFormParser userParser = new UserFormParser();
    private static final UserInfoFormParser userInfoParser = new UserInfoFormParser();

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
                try {
                    User staff = userParser.parse(this,userParameters);
                    staff.setUserRole(Role.STAFF);
                    UserService userService = (UserService) factory
                            .createService(DAOEnum.USER);
                    UserInfo staffuserInfo = userInfoParser.parse(this,userInfoParameters);
                    UserInfoService userInfoService = (UserInfoService)
                            factory.createService(DAOEnum.USERINFO);
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
