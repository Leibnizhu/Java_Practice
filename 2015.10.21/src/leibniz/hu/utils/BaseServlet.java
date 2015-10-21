package leibniz.hu.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1654294150922638543L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置请求的编码类型
		req.setCharacterEncoding("UTF-8");
		String command = req.getParameter("cmd");
		//如果无此参数或参数值为空，则执行默认的方法execute
		if(null == command || "".equals(command.trim())){
			command = "execute";
		}
		try {
			//使用反射执行指定方法
			Method method = this.getClass().getMethod(command, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	//必须由子类实现的一个默认执行方法
	public abstract void execute(HttpServletRequest req, HttpServletResponse resp);
}
