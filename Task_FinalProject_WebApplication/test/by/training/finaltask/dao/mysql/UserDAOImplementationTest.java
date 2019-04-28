package by.training.finaltask.dao.mysql;

import by.training.finaltask.entity.Role;
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
    //todo: fix deletion
    @Test
    public void testDelete() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            UserDAOImplementation userDAOImplementation = new UserDAOImplementation(connection);
            userDAOImplementation.delete(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            UserDAOImplementation userDAOImplementation = new UserDAOImplementation(connection);
            userDAOImplementation.add(new User(3,"Vladimir11",
                    "5d15a707538339a7a1877433a059cdf6", Role.valueOf(2)));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testGet1() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            UserDAOImplementation userDAOImplementation = new UserDAOImplementation(connection);
            User user = userDAOImplementation.get();
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete1() {
    }
}