package by.training.finaltask.action;

import by.training.finaltask.entity.Entity;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.parser.FormParser;
import by.training.finaltask.service.serviceinterface.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public abstract class Action {

	private User authorizedUser;
	private String name;

	public ServiceFactory factory;

	public User getAuthorizedUser() {
		return authorizedUser;
	}

	public void setAuthorizedUser(User authorizedUser) {
		this.authorizedUser = authorizedUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setServiceFactory(ServiceFactory factory) {
		this.factory = factory;
	}

	abstract public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException;

	public static class Forward {

		private String forward;
		private boolean redirect;
		private Map<String, Object> attributes = new HashMap<>();

		protected ServiceFactory factory;

		public Forward(String forward, boolean redirect) {
			this.forward = forward;
			this.redirect = redirect;
		}

		public Forward(String forward) {
			this(forward, true);
		}

		public String getForward() {
			return forward;
		}

		public void setForward(String forward) {
			this.forward = forward;
		}

		public boolean isRedirect() {
			return redirect;
		}

		public void setRedirect(boolean redirect) {
			this.redirect = redirect;
		}

		public Map<String, Object> getAttributes() {
			return attributes;
		}
	}
}
