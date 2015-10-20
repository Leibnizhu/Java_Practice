package leibniz.hu.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KickOut extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4862741211954083220L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 * �߳�ָ��Session ID���û�
	 */
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�ж�IP�Ƿ�����������������ý����߳��Ĳ���
		if(!"0:0:0:0:0:0:0:1".equals(request.getRemoteAddr())){
			System.out.println("Ȩ�޲������뼰ʱ��ֵ!!!"+request.getRemoteAddr());
			return;
		}
		String id = request.getParameter("id");
		Map<String, HttpSession> onlineMap = (Map<String, HttpSession>) getServletContext().getAttribute("online");
		HttpSession session = onlineMap.get(id);
		//ǿ��remove��user���ԣ�����������ɾ��Map�ж�Ӧ�ļ�ֵ��
		session.removeAttribute("user");
		//�ض����б�ҳ��
		response.sendRedirect(request.getContextPath() + "/showOnline");
	}

}
