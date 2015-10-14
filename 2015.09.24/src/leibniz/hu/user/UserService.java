package leibniz.hu.user;

import leibniz.hu.domain.User;

public class UserService {
	private UserDAO userDao = new UserDAO();
	//调用DAO去查询，降低耦合度
	public User login(User user) {
		return userDao.login(user);
	}

}
