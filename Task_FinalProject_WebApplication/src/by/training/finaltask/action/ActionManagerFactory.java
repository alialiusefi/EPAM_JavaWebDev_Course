package by.training.finaltask.action;

import by.training.finaltask.service.serviceinterface.ServiceFactory;

public class ActionManagerFactory {
	public static ActionManager getManager(ServiceFactory factory) {
		return new ActionManagerImpl(factory);
	}
}
