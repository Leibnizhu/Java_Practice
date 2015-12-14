package leibniz.hu.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
		} 
		return newUser;
	}

}
