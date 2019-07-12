package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface UserService extends Service {
    List<User> findAll(int offset, int rowcount) throws PersistentException;

    User findById(Integer id) throws PersistentException;

    User findByUserNameAndPassword(String user, String pass) throws PersistentException;

    Integer add(User user) throws PersistentException;

    void update(User user) throws PersistentException;

    void delete(Integer identity) throws PersistentException;

    int getAmountOfAllStaff() throws PersistentException;

    int getAmountOfAllStaffByFirstName(String firstname) throws PersistentException;

    int getAmountOfAllStaffByPhone(long phone) throws PersistentException;

    List<User> getAllStaff(int offset, int rowcount) throws PersistentException;

    List<User> getAllStaffByFirstName(String firstname, int offset, int rowcount) throws  PersistentException;

    List<User> getAllStaffByPhone(long phone, int offset, int rowcount) throws PersistentException;

    }
