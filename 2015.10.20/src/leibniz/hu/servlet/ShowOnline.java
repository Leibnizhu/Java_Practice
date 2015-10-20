package leibniz.hu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShowOnline extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706603038466795408L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 * 
	 * ����ĿContext�����ڴ洢�����û�Session��Mapת��Ϊһ��List����������ʾ��Jspҳ���ȡ��������
	 */
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ServletContext sc = request.getServletContext();
		Map<String, HttpSession> onlineMap = (Map<String, HttpSession>) getServletContext().getAttribute("online");
		//����Ҫת������һ��List
		List<Map<String, Object>> onlineList = new ArrayList<Map<String, Object>>();
		if(onlineMap != null){
			//�������ڸ�ʽ
			SimpleDateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//��������Map
			for(Entry<String, HttpSession> entry : onlineMap.entrySet()){
				//����һ��Map�����ڴ洢ÿ�������û��ĸ�����Ϣ
				Map<String, Object> onUser = new HashMap<String, Object>();
				HttpSession session = entry.getValue();
				onUser.put("id", entry.getKey());
				onUser.put("name", session.getAttribute("user"));
				onUser.put("ip", session.getAttribute("ip"));
				onUser.put("createTime", dfTemp.format(new Date(session.getCreationTime())));
				onUser.put("lastTime", dfTemp.format(new Date(session.getLastAccessedTime())));
				//�����Map����List��
				onlineList.add(onUser);
			}
			//�����List���浽Request��������
			request.setAttribute("onlineList", onlineList);
		}
		//���ת����������ʾ��Щ���ݵ�Jspҳ��
		request.getRequestDispatcher("/showOnline.jsp").forward(request, response);
	}
}
