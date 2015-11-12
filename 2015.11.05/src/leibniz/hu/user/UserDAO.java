package leibniz.hu.user;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import leibniz.hu.utils.DataSourceUtil;

public class UserDAO {

	/**
	 * @param name
	 * @return
	 * 根据用户名返回对应的User对象
	 * 用于判断该用户名是否存在对应的注册用户
	 */
	public User queryByName(String name) {
		String sql = "select * from users where name=?";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		User user = null;
		try {
			user = qRun.query(sql, new BeanHandler<User>(User.class), name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * @param user
	 * @return
	 * 根据用户名和密码登录
	 */
	public User login(User user) {
		String sql = "select * from users where name=? and pswd=?";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		User rUser = null;
		try {
			rUser = qRun.query(sql, new BeanHandler<User>(User.class), user.getName(), user.getPswd());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rUser;
	}

	public void register(User newUser) {
		String sql = "insert into users(id, name, pswd, email) values(?,?,?,?)";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		try {
			qRun.update(sql, newUser.getId(), newUser.getName(), newUser.getPswd(), newUser.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
