package leibniz.hu.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import leibniz.hu.domain.User;
import leibniz.hu.utils.DataSourceUtil;

public class UserDAO {

	public User login(User user) {
		User newUser = null;
		String sql = "select * from s_user where loginName = ? and loginPswd = ?";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		try {
			newUser = qRun.query(sql, new BeanHandler<User>(User.class), user.getLoginName(), user.getLoginPswd());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.remove();
		}
		return newUser;
	}

	public List<User> list() {
		List<User> userList = null;
		String sql = "select * from s_user";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		try {
			userList = qRun.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
		DataSourceUtil.remove();
		}
		return userList;
	}


}
