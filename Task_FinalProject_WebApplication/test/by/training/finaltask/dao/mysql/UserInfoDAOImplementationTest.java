package by.training.finaltask.dao.mysql;

import by.training.finaltask.entity.Role;
import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;
import org.testng.annotations.Test;

import javax.sound.midi.SysexMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import static org.testng.Assert.*;

public class UserInfoDAOImplementationTest {

    @Test
    public void testGet() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            UserInfoDAOImplementation userInfoDAOImplementation = new UserInfoDAOImplementation(connection);
            UserInfo actual = userInfoDAOImplementation.get(1);
            System.out.println(actual);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDelete() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            UserInfoDAOImplementation userInfoDAOImplementation = new UserInfoDAOImplementation(connection);
            boolean actual = userInfoDAOImplementation.delete(1);
            System.out.println(actual);
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
            UserInfoDAOImplementation userInfoDAOImplementation = new UserInfoDAOImplementation(connection);
            List<UserInfo> actual = userInfoDAOImplementation.getAll();
            actual.forEach(System.out::println);
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
            UserInfoDAOImplementation userInfoDAOImplementation = new UserInfoDAOImplementation(connection);
            boolean actual = userInfoDAOImplementation.add(
                    new UserInfo(
              1,
              "micheal11@gmail.com",
              "Micheal",
              "Scott",
              new GregorianCalendar(1999,11,0),
              "Scranton 11020 USA",
              1056989894
                    )
            );
            System.out.println(actual);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet1() {
        //wait for question
    }

    @Test
    public void testUpdate() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            UserInfoDAOImplementation userInfoDAOImplementation = new UserInfoDAOImplementation(connection);
            boolean user = userInfoDAOImplementation.update(
        new UserInfo(
                1,
                "micheal11@gmail.com",
                "Micheal",
                "Scott CHANGED",
                new GregorianCalendar(1999,11,0),
                "Scranton 11020 USA",
                1056989894
        )
            );

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