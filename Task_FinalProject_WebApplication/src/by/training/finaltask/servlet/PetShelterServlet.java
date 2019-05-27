package by.training.finaltask.servlet;

import by.training.finaltask.action.Action;
import by.training.finaltask.action.ActionManager;
import by.training.finaltask.action.ActionManagerFactory;
import by.training.finaltask.dao.pool.ConnectionPool;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.ServiceFactoryImpl;
import by.training.finaltask.service.serviceinterface.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

final public class PetShelterServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(PetShelterServlet.class);
    private PetShelterServletConfig config;

    @Override
    public void init() throws ServletException {
        config = new PetShelterServletConfig();
        try {
            //TODO: correctly configure root logger
            ConnectionPool.getInstance().initialize(
                    config.getDbDriverClass(),
                    config.getDbURL(),
                    config.getDbUser(),
                    config.getDbPassword(),
                    config.getDbPoolStartSize(),
                    config.getDbPoolMaxSize(),
                    config.getDbPoolCheckTimeOut()
            );
            LOGGER.debug("Servlet Initiated Succesfully!");
        } catch (PersistentException e) {
            LOGGER.error("Cannot Start Servlet:\n" + e.getMessage(), e);
            destroy();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestHandler(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestHandler(req, resp);
    }

    public ServiceFactory getFactory() throws PersistentException {
        return new ServiceFactoryImpl();
    }

    private void requestHandler(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Action action = (Action) request.getAttribute("action");
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> attributes = (Map<String, Object>) session.getAttribute("redirectedData");
                if (attributes != null) {
                    for (String key : attributes.keySet()) {
                        request.setAttribute(key, attributes.get(key));
                    }
                    session.removeAttribute("redirectedData");
                }
            }
            ActionManager actionManager = ActionManagerFactory.getManager(getFactory());
            Action.Forward forward = actionManager.execute(action, request, response);
            actionManager.close();
            if (session != null && forward != null && !forward.getAttributes().isEmpty()) {
                session.setAttribute("redirectedData", forward.getAttributes());
            }
            String requestedUri = request.getRequestURI();
            if (forward != null && forward.isRedirect()) {
                String redirectedUri = request.getContextPath() + forward.getForward();
                LOGGER.debug(String.format(
                        "Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri,
                        redirectedUri));
                response.sendRedirect(redirectedUri);
            } else {
                String jspPage;
                if (forward != null) {
                    jspPage = forward.getForward();
                } else {
                    jspPage = action.getName() + ".jsp";
                }
                jspPage = "/jsp" + jspPage;
                LOGGER.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
                getServletContext().getRequestDispatcher(jspPage).forward(request, response);
            }
        } catch (PersistentException e) {
            LOGGER.error("It is impossible to process request", e);
            request.setAttribute("message", "Error processing data!");
            getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    public PetShelterServletConfig getConfig() {
        return config;
    }
}
