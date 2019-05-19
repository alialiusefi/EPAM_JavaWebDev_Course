package by.training.finaltask.action;

import by.training.finaltask.exception.PersistentException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction extends Action {

	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {

		/*List<MenuItem> menu = (List<MenuItem>)request.getSession(false).getAttribute("menu");
		return new Forward(menu.get(0).getUrl());*/
		return new Forward("/index.jsp");
		}
}
