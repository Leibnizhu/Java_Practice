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
 * �������BaseServlet��ʵ���������ܣ�
 * 1. ���������е�cmd������ͨ������ִ�ж�Ӧ�ķ���
 * 2. �ڵ����귴��ķ����󣬸��ݷ���ֵ���������ض�����ת���������ӣ�����������ֵ��redirect��ͷ���ض������࿪ͷ��ת��������null������
 */
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
			Object result = method.invoke(this, req, resp);
			//�Է���ֵ�����ж�
			if(null != result){
				boolean isRedirect = false;
				String key = result.toString();
				if(key.startsWith("redirect:")){
					//�����redirect��ͷ������Ҫ�ض��򣬴�ʱ������ض�������ĵ�ַkeyֵ
					key = key.split(":")[1]; // ð�ŷָ�󣬵�һ����redirect�� �ڶ�����������ĵ�ַkey
					isRedirect = true;
				}
				//ʹ�ù����࣬��properties�ļ��и���key��ȡ
				String page = JumpPageUtils.getPage(key);
				if(null != page){
					if(isRedirect){
						//�ض���ָ��ҳ��
						resp.sendRedirect(req.getContextPath() + page);
					} else {
						//ת����ָ��ҳ��
						req.getRequestDispatcher(page).forward(req, resp);
					}
				} else {
					//��properties�ļ����Ҳ���ָ����key��Ӧ��ҳ�� ��ַ
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
	
	//����������ʵ�ֵ�һ��Ĭ��ִ�з���
	public abstract String execute(HttpServletRequest req, HttpServletResponse resp);
}
