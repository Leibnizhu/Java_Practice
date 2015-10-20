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
	 * �û��˳���¼ʱʹ�õ���Get����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��Session�е�user����ɾ�����ᴥ��attributeRemoved������
		request.getSession().removeAttribute("user");
		//�ض�����ҳ
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * �û���¼ʱʹ��Post������Я��user����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("user");
		//��Session�м���user���Լ�IP���ԣ��ᴥ��attributeAdded������
		HttpSession session = request.getSession();
		session.setAttribute("user", username);
		session.setAttribute("ip", request.getRemoteAddr());
		//�ض�����ҳ
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
