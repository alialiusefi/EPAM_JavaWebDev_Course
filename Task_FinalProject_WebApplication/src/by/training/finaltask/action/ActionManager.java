package by.training.finaltask.action;

import by.training.finaltask.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ActionManager {
	Action.Forward execute(Action action, HttpServletRequest request, HttpServletResponse response) throws PersistentException;

	void close() throws PersistentException;
}
