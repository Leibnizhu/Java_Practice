package leibniz.hu.user;

import leibniz.hu.domain.User;

public class UserService {
	private UserDAO userDao = new UserDAO();
	//����DAOȥ��ѯ��������϶�
	public User login(User user) {
		return userDao.login(user);
	}

}
