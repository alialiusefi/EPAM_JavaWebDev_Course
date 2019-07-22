package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.InvalidFormDataException;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.UserFormParser;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.UserInfoService;
import by.training.finaltask.service.serviceinterface.UserService;
import by.training.finaltask.parser.FormParserEnum;
import by.training.finaltask.parser.FormParserFactory;
import by.training.finaltask.parser.UserInfoFormParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserEditAction extends AuthorizedUserAction {

    private static final FormParserFactory FORM_PARSER_FACTORY = new FormParserFactory();

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            User user = (User)session.getAttribute("authorizedUser");
            if(user != null)
            {
                UserInfoService userInfoService = (UserInfoService) new
                        ServiceFactoryImpl().createService(DAOEnum.USERINFO);
                UserInfo userInfo = userInfoService.findById(user.getId());
                session.setAttribute("authorizedUserInfo",userInfo);
                List<String> userParameters = new ArrayList<>();
                List<String> userInfoParameters = new ArrayList<>();
                addUserParametersToList(request, userParameters);
                addUserInfoParametersToList(request, userInfoParameters);
                if(userParameters.get(1) == null || userParameters.get(1).isEmpty())
                {
                    request.setAttribute("message","inputPasswordToSubmit");
                    return null;
                }
                UserFormParser userValidator = (UserFormParser) FORM_PARSER_FACTORY.getValidator(
                        FormParserEnum.USERFORM);
                UserInfoFormParser userInfoFormValidator = (UserInfoFormParser) FORM_PARSER_FACTORY.getValidator(
                        FormParserEnum.USERINFOFORM);
                try {
                    User newUser = userValidator.parse(userParameters);
                    UserService userService = (UserService) new ServiceFactoryImpl().createService(
                            DAOEnum.USER);
                    newUser.setId(user.getId());
                    newUser.setUserRole(user.getUserRole());
                    UserInfo newUserInfo = userInfoFormValidator.parse(userInfoParameters);
                    newUserInfo.setId(user.getId());
                    userService.update(newUser);
                    userInfoService.update(newUserInfo);
                    session.setAttribute("authorizedUser",newUser);
                    session.setAttribute("userinfo",newUserInfo);
                    return new Forward("/user/profile.html",true);
                } catch (InvalidFormDataException e) {
                    request.setAttribute("message", e.getMessage());
                    return null;
                }
            } else {
                session.setAttribute("message","forbiddenAccess");
                return new Forward("/jsp/error.html",true);
            }
        }
        session.setAttribute("message","forbiddenAccess");
        return new Forward("/jsp/error.html",true);
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
