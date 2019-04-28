package by.training.finaltask.dao.mysql;

import by.training.finaltask.entity.Adoption;
import by.training.finaltask.entity.Pet;
import by.training.finaltask.exception.PersistentException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class PetDAOImplementationTest {

    @Test
    public void testGet() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/petshelter","root","");
            PetDAOImplementation petDAOImplementation= new PetDAOImplementation(connection);
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
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testUpdate() {
    }
}