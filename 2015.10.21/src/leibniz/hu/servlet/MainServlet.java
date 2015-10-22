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
		//��Session�ж�ȡ��½ʱ�洢��User���󣬶����Ǵ�request��ParametersŶ
		User user = (User) req.getSession().getAttribute("user");
		//ʹ��MenuService��ѯ���û�Ӧ��ӵ�еĲ˵�List
		if(null == user){
			try {
				resp.sendRedirect(req.getContextPath() + "/jsp/main.jsp?error=3");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		List<Menu> lsMenu = ms.query(user.getId());
		//����ѯ�����ӵ�Request�в�ת����JSPȥ��ʾ
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
