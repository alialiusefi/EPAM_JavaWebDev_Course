package by.training.finaltask.dao.mysql;

import by.training.finaltask.entity.Pet;
import by.training.finaltask.entity.PetStatus;
import by.training.finaltask.exception.PersistentException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

public class PetDAOImplementationTest {

    @Test
    public void testGet() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter", "root", "");
            PetDAOImplementation petDAOImplementation = new PetDAOImplementation(connection);
            Pet pet = petDAOImplementation.get(1);
            System.out.println(pet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        List<Pet> petList;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter", "root", "");
            PetDAOImplementation petDAOImplem = new PetDAOImplementation(connection);
            petList = petDAOImplem.getAll();
            petList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter", "root", "");
            PetDAOImplementation petDAOImplementation = new PetDAOImplementation(connection);
            boolean actual = petDAOImplementation.delete(1);
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter", "root", "");
            PetDAOImplementation petDAOImplementation = new PetDAOImplementation(connection);
            boolean actual = petDAOImplementation.add(
                    new Pet(
                            3,
                            "Fluffy",
                            "photos//3.jpg",
                            new GregorianCalendar(2015, 2, 5),
                            4.0,
                            new GregorianCalendar(2017, 8, 1),
                            2,
                            2,
                            PetStatus.valueOf("ADOPTED")
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
    public void testUpdate() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter", "root", "");
            PetDAOImplementation petDAOImplementation = new PetDAOImplementation(connection);
            boolean actual = petDAOImplementation.update(
                    new Pet(
                            3,
                            "Fluffy",
                            "photos//3.jpg",
                            new GregorianCalendar(2015, 2, 5),
                            4.0,
                            new GregorianCalendar(2017, 8, 1),
                            2,
                            2,
                            PetStatus.valueOf("ADOPTED")
                    )
            );
            System.out.println(actual);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}