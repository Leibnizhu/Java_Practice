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
	 * 将项目Context中用于存储在线用户Session的Map转换为一个List，方便在显示的Jsp页面获取各种数据
	 */
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ServletContext sc = request.getServletContext();
		Map<String, HttpSession> onlineMap = (Map<String, HttpSession>) getServletContext().getAttribute("online");
		//声明要转换到的一个List
		List<Map<String, Object>> onlineList = new ArrayList<Map<String, Object>>();
		if(onlineMap != null){
			//设置日期格式
			SimpleDateFormat dfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//遍历整个Map
			for(Entry<String, HttpSession> entry : onlineMap.entrySet()){
				//声明一个Map，用于存储每个在线用户的各种信息
				Map<String, Object> onUser = new HashMap<String, Object>();
				HttpSession session = entry.getValue();
				onUser.put("id", entry.getKey());
				onUser.put("name", session.getAttribute("user"));
				onUser.put("ip", session.getAttribute("ip"));
				onUser.put("createTime", dfTemp.format(new Date(session.getCreationTime())));
				onUser.put("lastTime", dfTemp.format(new Date(session.getLastAccessedTime())));
				//将这个Map放入List中
				onlineList.add(onUser);
			}
			//将这个List保存到Request的属性中
			request.setAttribute("onlineList", onlineList);
		}
		//最后转发给负责显示这些数据的Jsp页面
		request.getRequestDispatcher("/showOnline.jsp").forward(request, response);
	}
}
