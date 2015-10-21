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
	 * ��ָ����User����ѯ�����ݿ����Ƿ���ڣ��������ڷ���null�������ڣ����ط�װ���User
	 * ʹ����BeanHandler���򻯲���
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
