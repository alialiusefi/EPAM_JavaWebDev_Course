package by.training.finaltask.dao.mysql;

import java.sql.Connection;
import java.util.Properties;
import java.util.ResourceBundle;

abstract public class BaseDAO {

    protected Connection connection;
    protected ResourceBundle resourceBundle;

    protected BaseDAO(Connection connection)
    {
        this.connection = connection;
    }
    protected Connection getConnection() {
        return this.connection;
    }


}
