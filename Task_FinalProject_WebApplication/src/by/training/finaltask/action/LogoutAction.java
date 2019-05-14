package by.training.finaltask.action;

import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends AuthorizedUserAction {

    Logger logger = LogManager.getLogger(LogoutAction.class);

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("authorizedUser");
        logger.info(user.getUsername() + " has logged out!");
        session.invalidate();
        return new Forward("/login.html");
    }
}
