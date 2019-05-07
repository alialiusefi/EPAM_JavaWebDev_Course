package by.training.finaltask.servlet;

import java.util.ResourceBundle;
import java.util.logging.Level;

public final class PetShelterServletConfig {

    private String logFilename;
    private Level logLevel;
    private String logFormat;
    private String dbDriverClass;
    private String dbURL;
    private String dbUser;
    private String dbPassword;
    private int dbPoolStartSize;
    private int dbPoolMaxSize;
    private int dbPoolCheckTimeOut;

    public PetShelterServletConfig()
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(
                "servletconfig");
        logFilename = resourceBundle.getString("logFilename");
        logLevel = Level.parse(resourceBundle.getString("ALL"));
        logFormat = resourceBundle.getString("logFormat");
        dbDriverClass = resourceBundle.getString("dbDriverClass");
        dbURL = resourceBundle.getString("dbURL");
        dbPoolStartSize = Integer.parseInt(
                resourceBundle.getString("dbPoolMaxSize"));
        dbPoolMaxSize = Integer.parseInt(
                resourceBundle.getString("dbPoolMaxSize"));
        dbPoolCheckTimeOut = Integer.parseInt(
                resourceBundle.getString("dbPoolCheckTimeOut"));
        dbUser = resourceBundle.getString("dbUser");
        dbPassword = resourceBundle.getString("dbPassword");
    }

    public String getLogFilename() {
        return logFilename;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public String getLogFormat() {
        return logFormat;
    }

    public String getDbDriverClass() {
        return dbDriverClass;
    }

    public String getDbURL() {
        return dbURL;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getDbPoolStartSize() {
        return dbPoolStartSize;
    }

    public int getDbPoolMaxSize() {
        return dbPoolMaxSize;
    }

    public int getDbPoolCheckTimeOut() {
        return dbPoolCheckTimeOut;
    }
}
