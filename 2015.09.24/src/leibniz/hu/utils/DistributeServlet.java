package leibniz.hu.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Leibniz
 * ����һ������HttpServlet�ĳ����࣬���ڷַ�����get��postͬһ����
 * ���������е�method���������ݣ�ȷ��Ҫִ�еķ���
 * ��ͨ�������ø÷���Ȼ��ִ�С�
 * ����һ������ʵ�ֵ�execute()������ΪĬ�ϴ���ķ���
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
		//���ñ��벢��ȡ����
		req.setCharacterEncoding("UTF-8");
		String methodName = req.getParameter("method");
		//����Ĭ�Ϸ����Ĵ���
		//��������������ڻ�Ϊ���ַ�������ִ��Ĭ�ϵķ���
		if(methodName == null || "".equals(methodName.trim())){
			methodName = "execute";
		}
		try {
			//�����ȡ������ִ��֮
			Method  method = this.getClass().getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
