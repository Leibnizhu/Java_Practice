package leibniz.hu.servlet;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.User;
import leibniz.hu.service.UserService;
import leibniz.hu.utils.BaseServlet;

public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = -7142379703773095920L;
	private UserService us = new UserService();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			login(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//从请求中获取用户提交的用户名和密码参数
		String username = req.getParameter("username");
		String password = req.getParameter("pswd");
		//封装为User对象，并使用UserService进行查询，返回值传递给原User对象
		User uTemp = new User(null, username, password);
		uTemp = us.login(uTemp);
		if(null==uTemp){
			//如果返回空的User，则表示在数据库中查询不到满足该用户名和密码的记录，即用户名/密码错误
			//重定向到首页，让用户继续登录
			System.out.println("登录失败...用户名：" + username + "...密码：" + password + "...");
			resp.sendRedirect(req.getContextPath() + "/index.jsp?error=1");
		} else {
			//若不为空，即用户名和密码正确，重定向到主页面的Servlet
			//要进行进一步查询，有哪些菜单，再将结果返回让JSP显示
			req.getSession().setAttribute("user", uTemp);
			resp.sendRedirect(req.getContextPath() + "/mainServlet");
		}
	}

}
