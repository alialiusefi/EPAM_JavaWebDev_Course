package by.training.finaltask.action.guest;

import by.training.finaltask.action.AuthorizedUserAction;
import by.training.finaltask.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAdoptionsAction extends AuthorizedUserAction {

    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
        return null;
    }

}
