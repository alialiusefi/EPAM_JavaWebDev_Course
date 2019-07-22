package by.training.finaltask.action;

import by.training.finaltask.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends AuthorizedUserAction {

    private Logger logger = LogManager.getLogger(LogoutAction.class);

    @Override
    public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            User user = (User)session.getAttribute("authorizedUser");
            if(user != null)
            {
                logger.info(user.getUsername() + " has logged out!");
                session.invalidate();
                request.setAttribute("message","loggedOutSuccessfully");
                return new Forward("/login.html");
            } else {
                request.setAttribute("message","logoutError");
                return new Forward("/login.html");
            }
        }
        return new Forward("/login.html");
    }
}
