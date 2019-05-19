package by.training.finaltask.service;

import by.training.finaltask.entity.User;
import by.training.finaltask.entity.UserInfo;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.UserInfoService;

import javax.xml.ws.Service;
import java.sql.Connection;
import java.util.List;

public class UserInfoServiceImpl extends ServiceImpl implements UserInfoService {

    public UserInfoServiceImpl(Connection connection)
    {
        super(connection);
    }


    @Override
    public List<User> findAll() throws PersistentException {
        return null;
    }

    @Override
    public User findByIdentity(Integer id) throws PersistentException {
        return null;
    }

    @Override
    public void add(UserInfo userinfo) throws PersistentException {

    }

    @Override
    public void update(UserInfo userinfo) throws PersistentException {

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

    }
}
