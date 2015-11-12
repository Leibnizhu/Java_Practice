package leibniz.hu.user;

public class UserService {
	private UserDAO userDao = new UserDAO();

	public User login(User user) {
		return userDao.login(user);
	}

	public User queryByName(String name) {
		return userDao.queryByName(name);
	}

	public void register(User newUser) {
		userDao.register(newUser);
	}
	
}
