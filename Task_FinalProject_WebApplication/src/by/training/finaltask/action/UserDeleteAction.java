package by.training.finaltask.action;

import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.UserServiceImpl;
import by.training.finaltask.service.serviceinterface.UserInfoService;
import by.training.finaltask.service.serviceinterface.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserDeleteAction extends AuthorizedUserAction {

    private static Logger LOGGER = LogManager.getLogger(UserDeleteAction.class);

    public UserDeleteAction(){
        this.allowedRoles.add(Role.GUEST);
        this.allowedRoles.add(Role.ADMINISTRATOR);
    }

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            User user = (User)session.getAttribute("authorizedUser");
            if(user != null && allowedRoles.contains(user.getUserRole()))
            {
                UserService userService = (UserService)new ServiceFactoryImpl().createService(DAOEnum.USER);
                UserInfoService userInfoService = (UserInfoService) new ServiceFactoryImpl().createService(DAOEnum.USERINFO);
                userService.delete(user.getId());
                userInfoService.delete(user.getId());
                return new Forward("/logout.html",true);
            }
            else {
                session.setAttribute("message","forbiddenAccess");
                return new Forward("/jsp/error.html",true);
            }
        }
        return new Forward("/jsp/error.html",true);
    }
}
