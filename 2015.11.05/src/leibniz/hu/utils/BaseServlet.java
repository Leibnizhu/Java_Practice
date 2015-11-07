package leibniz.hu.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Leibniz
 * 改良版的BaseServlet，实现两个功能：
 * 1. 根据请求中的cmd参数，通过反射执行对应的方法
 * 2. 在调用完反射的方法后，根据返回值，决定是重定向还是转发还是无视，当方法返回值以redirect开头则重定向，其余开头则转发，返回null则无视
 */
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
			Object result = method.invoke(this, req, resp);
			//对返回值进行判断
			if(null != result){
				boolean isRedirect = false;
				String key = result.toString();
				if(key.startsWith("redirect:")){
					//如果以redirect开头，则需要重定向，此时分离出重定向所需的地址key值
					key = key.split(":")[1]; // 冒号分割后，第一个是redirect， 第二个才是所需的地址key
					isRedirect = true;
				}
				//使用工具类，从properties文件中根据key读取
				String page = JumpPageUtils.getPage(key);
				if(null != page){
					if(isRedirect){
						//重定向到指定页面
						resp.sendRedirect(req.getContextPath() + page);
					} else {
						//转发到指定页面
						req.getRequestDispatcher(page).forward(req, resp);
					}
				} else {
					//在properties文件中找不到指定的key对应的页面 地址
					throw new RuntimeException("No such redirect/foward page key: " + key);
				}
			}
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
	public abstract String execute(HttpServletRequest req, HttpServletResponse resp);
}
