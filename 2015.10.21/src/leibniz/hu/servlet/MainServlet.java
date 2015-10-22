package leibniz.hu.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.Menu;
import leibniz.hu.domain.User;
import leibniz.hu.service.MenuService;
import leibniz.hu.utils.BaseServlet;

public class MainServlet extends BaseServlet {
	private static final long serialVersionUID = 8698946070152281772L;
	private MenuService ms = new MenuService();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//从Session中读取登陆时存储的User对象，而不是从request的Parameters哦
		User user = (User) req.getSession().getAttribute("user");
		//使用MenuService查询该用户应该拥有的菜单List
		if(null == user){
			try {
				resp.sendRedirect(req.getContextPath() + "/jsp/main.jsp?error=3");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		List<Menu> lsMenu = ms.query(user.getId());
		//将查询结果添加到Request中并转发给JSP去显示
		req.setAttribute("lsMenu", lsMenu);
		try {
			req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
