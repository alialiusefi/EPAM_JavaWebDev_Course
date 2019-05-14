package by.training.finaltask.action;

import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileAction extends AuthorizedUserAction {

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        User user = getAuthorizedUser();
        HttpSession session = request.getSession(false);
        if(user != null && session != null){

            return new Forward("/user/profile.html");
        }
        return new Forward("/index.html",true);
    }
}
