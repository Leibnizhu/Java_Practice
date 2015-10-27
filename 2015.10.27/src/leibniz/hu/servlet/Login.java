package leibniz.hu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import leibniz.hu.domain.User;
import leibniz.hu.utils.DataSourceUtil;

public class Login extends HttpServlet {

	private static final long serialVersionUID = -8449422824200624279L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取各种参数构造User对象
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		try{
			QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
			//使用左连接，将没有激活码的用户也加入查询结果
			String sql = "Select id,name,pwd,email,accode " +
						"from users left join active on users.id=active.uid " +
						"where name=? and pwd=?";
			User user = qRun.query(sql, new BeanHandler<User>(User.class), name, pwd);
			if(user == null){
				//查询不到符合输入用户名和密码的记录
				//用户名或密码错误
				response.sendRedirect(request.getContextPath() + "/index.jsp?error=2");
			} else {
				//用户名和密码正确
				if(user.getCode() == null){
					//没有对应的激活码，证明已激活成功
					System.out.println("登录成功");
				} else{
					//有激活码，证明还没激活
					response.sendRedirect(request.getContextPath() + "/index.jsp?error=3");
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
