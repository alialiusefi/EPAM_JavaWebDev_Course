package by.training.finaltask.dao.mysql;

import java.sql.Connection;
import java.util.Properties;
import java.util.ResourceBundle;
/*
External files (e.g. Properties or Resource files)

Pros

    SQL can be changed without a need to rebuild the application
    Decouples the SQL logic from the application business logic
    Central repository of all SQL statements – easier to maintain
    Easier to understand

Cons:

    SQL code can become un-maintainable
    Harder to check the SQL code for (syntax) errors


 */



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
