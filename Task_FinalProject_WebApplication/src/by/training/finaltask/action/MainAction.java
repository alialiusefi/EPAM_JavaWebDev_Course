package by.training.finaltask.action;

import by.training.finaltask.exception.PersistentException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainAction extends AuthorizedUserAction {
	@Override
	public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		@SuppressWarnings("unchecked")
		List<MenuItem> menu = (List<MenuItem>)request.getSession(false).getAttribute("menu");
		System.out.println(menu);
		System.out.println(menu.get(0));
		return new Action.Forward(menu.get(0).getUrl());
	}
}
