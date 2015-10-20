package leibniz.hu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -853100759161880647L;


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 用户退出登录时使用的是Get方法
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//将Session中的user属性删除，会触发attributeRemoved监听器
		request.getSession().removeAttribute("user");
		//重定向到首页
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 用户登录时使用Post方法，携带user属性
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("user");
		//往Session中加入user属性及IP属性，会触发attributeAdded监听器
		HttpSession session = request.getSession();
		session.setAttribute("user", username);
		session.setAttribute("ip", request.getRemoteAddr());
		//重定向到首页
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
