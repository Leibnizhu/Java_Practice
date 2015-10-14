package leibniz.hu.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.User;
import leibniz.hu.utils.DistributeServlet;

public class UserServlet extends DistributeServlet {

	private static final long serialVersionUID = 4230766353776938248L;
	private UserService us = new UserService();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		//从请求中接受参数并封装成User对象
		String name = req.getParameter("username");
		String pswd = req.getParameter("pswd");
		User user = new User(null, name, pswd);
		//调用UserService的方法登录
		user = us.login(user);
		//返回null则不存在该用户
		if(user == null){
			System.err.println("用户名/密码错误，请重新输入...");
		} else {
			System.out.println("登录成功...");
			//将正确的用户对象加入到session属性中，并重定向
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("/contacts/showContacts");
		}
	}
	
	public void logOut(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//TODO
	}

	public void register(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//TODO
	}
}
