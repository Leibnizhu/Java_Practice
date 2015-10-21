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
		//��������ı�������
		req.setCharacterEncoding("UTF-8");
		String command = req.getParameter("cmd");
		//����޴˲��������ֵΪ�գ���ִ��Ĭ�ϵķ���execute
		if(null == command || "".equals(command.trim())){
			command = "execute";
		}
		try {
			//ʹ�÷���ִ��ָ������
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
	
	//����������ʵ�ֵ�һ��Ĭ��ִ�з���
	public abstract void execute(HttpServletRequest req, HttpServletResponse resp);
}
