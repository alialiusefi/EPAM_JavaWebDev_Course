package by.training.finaltask.dao.mysql;

import by.training.finaltask.entity.Adoption;
import by.training.finaltask.exception.PersistentException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

public class AdoptionDAOImplementationTest {

    @Test
    public void testGet() {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            AdoptionDAOImplementation adoptionDAOImplem = new AdoptionDAOImplementation(connection);
            Adoption adoption = adoptionDAOImplem.get(1);
            System.out.println(adoption);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        List<Adoption> adoptionList;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            AdoptionDAOImplementation adoptionDAOImplem = new AdoptionDAOImplementation(connection);
            adoptionList = adoptionDAOImplem.getAll();
            adoptionList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            AdoptionDAOImplementation adoptionDAOImplem = new AdoptionDAOImplementation(connection);
            boolean actual = adoptionDAOImplem.delete(1);
            System.out.println(actual);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            AdoptionDAOImplementation adoptionDAOImplem = new AdoptionDAOImplementation(connection);
            boolean actual = adoptionDAOImplem.add(
                    new Adoption(
                            1,
                            new GregorianCalendar(2000,3,1),
                            null,
                            1
                    )
            );
            System.out.println(actual);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }


    //todo: fix this!
    @Test
    public void testDelete1() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            AdoptionDAOImplementation adoptionDAOImplem = new AdoptionDAOImplementation(connection);
            boolean actual = adoptionDAOImplem.delete(
                    new Adoption(
                            1,
                            new GregorianCalendar(2019, 0,10),
                            new GregorianCalendar(2019, 1,2),
                            1
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
    }

    @Test
    public void testUpdate() {
    }
}