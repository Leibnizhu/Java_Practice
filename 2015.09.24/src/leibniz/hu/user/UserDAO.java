package leibniz.hu.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import leibniz.hu.domain.User;
import leibniz.hu.utils.ConnUtils;

public class UserDAO {

	public User login(User user) {
		User u = null;
		//从连接池获取连接
		Connection conn = ConnUtils.getConn();
		//sql查询
		String sql = "select * from users where username=? and pswd=?";
		try {
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPswd());
			ResultSet rs = pstat.executeQuery();
			//将查询结果封装为user对象并返回
			if(rs.next()){
				u = new User(rs.getString("id"), rs.getString("username"), rs.getString("pswd"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return u;
	}

}
