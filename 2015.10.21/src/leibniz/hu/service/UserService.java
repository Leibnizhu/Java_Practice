package leibniz.hu.service;

import leibniz.hu.DAO.UserDAO;
import leibniz.hu.domain.User;

public class UserService {
	private UserDAO userDao = new UserDAO();
	public User login(User uTemp) {
		return userDao.login(uTemp);
	}

}
