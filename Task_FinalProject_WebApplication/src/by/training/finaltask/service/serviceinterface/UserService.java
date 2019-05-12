package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.Service;

import java.util.List;

public interface UserService extends Service {
    List<User> findAll() throws PersistentException;

    User findByIdentity(Integer id) throws PersistentException;

    User findByUserNameAndPassword(String user, String pass) throws PersistentException;

    void add(User user) throws PersistentException;

    void update(User user) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}
