package leibniz.hu.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Leibniz
 * 定义一个集成HttpServlet的抽象类，用于分发请求，get和post同一处理
 * 根据请求中的method参数的内容，确定要执行的方法
 * 并通过反射获得该方法然后执行。
 * 定义一个必须实现的execute()方法作为默认处理的方法
 */
public abstract class DistributeServlet extends HttpServlet {
	private static final long serialVersionUID = 7315437268443783130L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
		this.doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		//设置编码并获取参数
		req.setCharacterEncoding("UTF-8");
		String methodName = req.getParameter("method");
		//设置默认方法的处理
		//如果方法名不存在或为空字符串，则执行默认的方法
		if(methodName == null || "".equals(methodName.trim())){
			methodName = "execute";
		}
		try {
			//反射获取方法并执行之
			Method  method = this.getClass().getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
