package by.training.finaltask.service.serviceinterface;

import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;

import java.util.List;

public interface UserInfoService extends Service {
    List<User> findAll() throws PersistentException;

    UserInfo findById(Integer id) throws PersistentException;

    Integer add(UserInfo userinfo) throws PersistentException;

    void update(UserInfo userinfo) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}
