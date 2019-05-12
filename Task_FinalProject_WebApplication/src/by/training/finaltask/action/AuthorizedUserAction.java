package by.training.finaltask.action;

import by.training.finaltask.entity.Role;

import java.util.Arrays;

public abstract class AuthorizedUserAction extends Action {
	public AuthorizedUserAction() {
		getAllowRoles().addAll(Arrays.asList(Role.values()));
	}
}
