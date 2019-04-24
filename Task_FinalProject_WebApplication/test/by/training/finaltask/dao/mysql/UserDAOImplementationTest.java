package by.training.finaltask.dao.mysql;

import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.*;

public class UserDAOImplementationTest {

    @Test
    public void testGet() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            UserDAOImplementation userDAOImplementation = new UserDAOImplementation(connection);
            User user = userDAOImplementation.get(1);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            UserDAOImplementation userDAOImplementation = new UserDAOImplementation(connection);
            List<User> users = userDAOImplementation.getAll();
            users.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testGet1() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete1() {
    }
}