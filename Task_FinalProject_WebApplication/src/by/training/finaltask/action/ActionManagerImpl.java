package by.training.finaltask.action;

import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionManagerImpl implements ActionManager {
	private ServiceFactory factory;

	public ActionManagerImpl(ServiceFactory factory) {
		this.factory = factory;
	}

	@Override
	public Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response)
			throws PersistentException {
		action.setFactory(factory);
		return action.exec(request, response);
	}

	@Override
	public void close() throws PersistentException {
		factory.close();
	}
}
