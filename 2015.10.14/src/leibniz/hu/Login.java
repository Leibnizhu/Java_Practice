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
	 * 用于处理退出登录
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除Session
		request.getSession().invalidate();
		//删除Cookie
		Cookie c =new Cookie("autoLogin","");
		c.setMaxAge(0);
		c.setPath(request.getContentType());
		response.addCookie(c);
		//在Session中加入exit属性，以便绕过Cookie遍历
		request.getSession().setAttribute("exit", true);
		//退出登录后重定向到index.jsp
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	/**
	 * 用于处理用户登录
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String auto = request.getParameter("auto");
		request.getSession().setAttribute("name", name); //将用户名信息加入到Session，以便在index。jsp中显示姓名
		if(!"-1".equals(auto)){
			//没选择不保持登录
			int ageSecond = Integer.parseInt(auto) * 60 * 60 * 24;
			//名字编码后加入到Cookie
			Cookie c = new Cookie("autoLogin",URLEncoder.encode(name,"UTF-8"));
			c.setMaxAge(ageSecond);
			c.setPath(request.getContextPath());
			response.addCookie(c);
		}
		//不管保不保存Cookie登录，都要重新进入index。jsp
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
