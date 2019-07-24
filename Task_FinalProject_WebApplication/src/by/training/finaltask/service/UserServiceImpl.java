package by.training.finaltask.service;

import by.training.finaltask.dao.daointerface.UserDAO;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;
import by.training.finaltask.service.serviceinterface.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.List;


public class UserServiceImpl extends ServiceImpl implements UserService {

    UserServiceImpl(Connection aliveConnection) {
        super(aliveConnection);
    }

    @Override
    public List<User> getAll(int offset, int rowcount) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            List<User> users = dao.getAll(offset, rowcount);
            commit();
            connection.setAutoCommit(true);
            return users;
        } catch (PersistentException | SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public User get(Integer id) throws PersistentException {
        UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
        return dao.get(id);
    }

    @Override
    public User findByUserNameAndPassword(String user, String pass) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            String md5Pass = md5(pass);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            User userFound = dao.get(user, md5Pass);
            commit();
            connection.setAutoCommit(true);
            return userFound;
        } catch (PersistentException | SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public Integer add(User user) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            int userIDGenerated = dao.add(user);
            commit();
            connection.setAutoCommit(true);
            return userIDGenerated;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(User user) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            dao.update(user);
            commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            dao.delete(identity);
            commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    public static String md5(String string) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            digest.reset();
            digest.update(string.getBytes());
            byte hash[] = digest.digest();
            Formatter formatter = new Formatter();
            for (int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String md5summ = formatter.toString();
            formatter.close();
            return md5summ;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @Override
    public int getAmountOfAllStaff() throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            int res = dao.getAmountOfAllStaff();
            commit();
            connection.setAutoCommit(true);
            return res;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public List<User> getAllStaff(int offset, int rowcount) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            List<User> staffs = dao.getAllStaff(offset, rowcount);
            commit();
            connection.setAutoCommit(true);
            return staffs;
        } catch (PersistentException | SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public List<User> getAllStaffByFirstName(String firstname, int offset, int rowcount) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            List<User> staffs = dao.getAllStaffByFirstName(firstname, offset, rowcount);
            commit();
            connection.setAutoCommit(true);
            return staffs;
        } catch (PersistentException | SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public List<User> getAllStaffByPhone(long phone, int offset, int rowcount) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            List<User> staffs = dao.getAllStaffByPhone(phone, offset, rowcount);
            commit();
            connection.setAutoCommit(true);
            return staffs;
        } catch (PersistentException | SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public int getAmountOfAllStaffByFirstName(String firstname) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            int res = dao.getAmountOfAllStaffByFirstName(firstname);
            commit();
            connection.setAutoCommit(true);
            return res;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

    @Override
    public int getAmountOfAllStaffByPhone(long phone) throws PersistentException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = (UserDAO) createDao(DAOEnum.USER);
            int res = dao.getAmountOfAllStaffByPhone(phone);
            commit();
            connection.setAutoCommit(true);
            return res;
        } catch (SQLException e) {
            rollback();
            throw new PersistentException(e);
        }
    }

}