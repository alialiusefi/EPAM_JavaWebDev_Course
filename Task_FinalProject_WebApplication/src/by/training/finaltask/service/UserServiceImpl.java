package by.training.finaltask.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

import by.training.finaltask.dao.daointerface.UserDAO;
import by.training.finaltask.dao.mysql.DAOEnum;
import by.training.finaltask.entity.User;
import by.training.finaltask.exception.PersistentException;

public class UserServiceImpl extends ServiceImpl implements UserService {
	@Override
	public List<User> findAll() throws PersistentException {
		UserDAO dao = (UserDAO)transaction.createDao(DAOEnum.USER);
		return dao.getAll();
	}

	@Override
	public User findByIdentity(Integer id) throws PersistentException {
		UserDAO dao = (UserDAO)transaction.createDao(DAOEnum.USER);
		return dao.get(id);
	}
	@Override
	public void add(User user) throws PersistentException {
		UserDAO dao = (UserDAO)transaction.createDao(DAOEnum.USER);
		dao.add(user);
	}
	@Override
	public void update(User user) throws PersistentException
	{
		UserDAO dao = (UserDAO)transaction.createDao(DAOEnum.USER);
		dao.update(user);
	}

	@Override
	public void delete(Integer identity) throws PersistentException {
		UserDAO dao = (UserDAO)transaction.createDao(DAOEnum.USER);
		dao.delete(identity);
	}

	private String md5(String string) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("md5");
			digest.reset();
			digest.update(string.getBytes());
			byte hash[] = digest.digest();
			Formatter formatter = new Formatter();
			for(int i = 0; i < hash.length; i++) {
				formatter.format("%02X", hash[i]);
			}
			String md5summ = formatter.toString();
			formatter.close();
			return md5summ;
		} catch(NoSuchAlgorithmException e) {
			return null;
		}
	}
}
