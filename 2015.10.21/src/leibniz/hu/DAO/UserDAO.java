package leibniz.hu.DAO;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import leibniz.hu.domain.User;
import leibniz.hu.utils.DataSourceUtil;

public class UserDAO {

	/**
	 * @param uTemp
	 * @return User
	 * 对指定的User，查询在数据库中是否存在，若不存在返回null，若存在，返回封装后的User
	 * 使用了BeanHandler，简化操作
	 */
	public User login(User uTemp) {
		String sql = "Select * from users where name=? and pwd=?";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		try {
			uTemp = qRun.query(sql, new BeanHandler<User>(User.class), uTemp.getName(), uTemp.getPwd());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uTemp;
	}

}
