package leibniz.hu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
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
		QueryRunner qRun = new QueryRunner();
		try {
			userList = qRun.query(DataSourceUtil.getConnNonTrans(), sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
		DataSourceUtil.remove();
		}
		return userList;
	}

	public List<User> select(User user) {
		//根据条件模糊查询
		List<User> userList = null;
		//获取查询的条件
		String userName = user.getUserName();
		String sex = user.getSex();
		String education = user.getEducation();
		String resume = user.getResume();
		//遍历四个条件，拼成sql语句，及相应的参数数组
		String sql = "select * from s_user where 1=1";
		List<Object> params = new ArrayList<Object>();
		if(null!=userName && !"".equals(userName.trim())){
			sql += " and userName LIKE ?";
			params.add("%" + userName + "%");
		}
		if(null!=sex && !"".equals(sex.trim())){
			sql += " and sex =?";
			params.add(sex);
		}
		if(null!=education && !"".equals(education.trim())){
			sql += " and education =?";
			params.add(education);
		}
		if(null!=resume && !"".equals(resume.trim())){
			sql += " and resume =?";
			params.add(resume);
		}
		System.out.println(sql);
		QueryRunner qRun = new QueryRunner();
		try {
			userList = qRun.query(DataSourceUtil.getConnNonTrans(), sql, new BeanListHandler<User>(User.class), params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			DataSourceUtil.remove();
		}
		return userList;
	}

	public void add(User user) {
		List<User> userList = null;
		String sql = "INSERT INTO s_user (userID, userName, loginName, loginPswd, sex, birthday, education, telephone, interest, resume, filename, remark) " +
					 " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		QueryRunner qRun = new QueryRunner();
		try {
			qRun.update(DataSourceUtil.getConnNonTrans(), sql, user.getUserID(), user.getUserName(), user.getLoginName(), user.getLoginPswd(),
					user.getSex(), user.getBirthday(), user.getEducation(), user.getTelephone(), user.getInterest(),
					user.getResume(), user.getFilename(), user.getRemark());
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			DataSourceUtil.remove();
		}
	}

	public User getUserById(String userID) {
		User user = null;
		String sql = "select * from s_user where userID = ?";
		QueryRunner qRun = new QueryRunner();
		try {
			user = qRun.query(DataSourceUtil.getConnNonTrans(), sql, new BeanHandler<User>(User.class), userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			DataSourceUtil.remove();
		}
		return user;
	}
}
