package by.training.finaltask.action;

import by.training.finaltask.entity.Role;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public abstract class AuthorizedUserAction extends Action {

	protected HttpSession session;

	protected Set<Role> allowedRoles = new HashSet<>();

	public Set<Role> getAllowedRoles() {
		return allowedRoles;
	}

}
