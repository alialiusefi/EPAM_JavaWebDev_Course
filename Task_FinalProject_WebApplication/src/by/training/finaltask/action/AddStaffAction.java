package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.UserInfoService;
import by.training.finaltask.service.serviceinterface.UserService;
import by.training.finaltask.validator.FormValidatorEnum;
import by.training.finaltask.validator.FormValidatorFactory;
import by.training.finaltask.validator.UserFormValidator;
import by.training.finaltask.validator.UserInfoFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddStaffAction extends AuthorizedUserAction {

    private static final FormValidatorFactory formValidatorFactory = new FormValidatorFactory();

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
                if (userParameters.stream().anyMatch(e -> e != null)
                        && userInfoParameters.stream().anyMatch(e -> e != null)) {
                    UserFormValidator userValidator = (UserFormValidator) formValidatorFactory.getValidator(
                            FormValidatorEnum.USERFORM);
                    UserInfoFormValidator userInfoFormValidator = (UserInfoFormValidator) formValidatorFactory.getValidator(
                            FormValidatorEnum.USERINFOFORM);
                    try {
                        User staff = userValidator.validate(userParameters);
                        staff.setUserRole(Role.STAFF);
                        UserService userService = (UserService) new ServiceFactoryImpl().createService(
                                DAOEnum.USER);
                        UserInfo staffuserInfo = userInfoFormValidator.validate(userInfoParameters);
                        UserInfoService userInfoService = (UserInfoService)
                                new ServiceFactoryImpl().createService(DAOEnum.USERINFO);
                        int userIDGenerated = userService.add(staff);
                        staffuserInfo.setId(userIDGenerated);
                        userInfoService.add(staffuserInfo);
                        request.setAttribute("message","staffAdded");
                    } catch (InvalidFormDataException e) {
                        request.setAttribute("message", e.getMessage());
                        return null;
                    }
                }  else {
                    return null;
                }
            }
            return new Forward("/login.html",true);
        }
        return new Forward("/login.html",true);
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
