package by.training.finaltask.servlet;

import by.training.finaltask.dao.pool.ConnectionPool;
import by.training.finaltask.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.layout.PatternLayout;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

final public class PetShelterServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(PetShelterServlet.class);
    private PetShelterServletConfig config;
    @Override
    public void init() throws ServletException {
        config = new PetShelterServletConfig();
        try{
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

        } catch (PersistentException /*| IOException*/ e)
        {
            LOGGER.error("Cannot Start Servlet:\n" + e.getMessage(),e);
            destroy();
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<h1>Hello!</h1>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public PetShelterServletConfig getConfig() {
        return config;
    }
}
