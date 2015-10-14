package leibniz.hu;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	/**
	 * ���ڴ����˳���¼
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ɾ��Session
		request.getSession().invalidate();
		//ɾ��Cookie
		Cookie c =new Cookie("autoLogin","");
		c.setMaxAge(0);
		c.setPath(request.getContentType());
		response.addCookie(c);
		//��Session�м���exit���ԣ��Ա��ƹ�Cookie����
		request.getSession().setAttribute("exit", true);
		//�˳���¼���ض���index.jsp
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	/**
	 * ���ڴ����û���¼
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String auto = request.getParameter("auto");
		request.getSession().setAttribute("name", name); //���û�����Ϣ���뵽Session���Ա���index��jsp����ʾ����
		if(!"-1".equals(auto)){
			//ûѡ�񲻱��ֵ�¼
			int ageSecond = Integer.parseInt(auto) * 60 * 60 * 24;
			//���ֱ������뵽Cookie
			Cookie c = new Cookie("autoLogin",URLEncoder.encode(name,"UTF-8"));
			c.setMaxAge(ageSecond);
			c.setPath(request.getContextPath());
			response.addCookie(c);
		}
		//���ܱ�������Cookie��¼����Ҫ���½���index��jsp
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
