package by.training.finaltask.action;

import by.training.finaltask.entity.Role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class AuthorizedUserAction extends Action {

	protected Set<Role> allowedRoles = new HashSet<>();

	public Set<Role> getAllowedRoles() {
		return allowedRoles;
	}

}
