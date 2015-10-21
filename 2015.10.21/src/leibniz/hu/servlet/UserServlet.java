package leibniz.hu.servlet;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.User;
import leibniz.hu.service.UserService;
import leibniz.hu.utils.BaseServlet;

public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = -7142379703773095920L;
	private UserService us = new UserService();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			login(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		//�������л�ȡ�û��ύ���û������������
		String username = req.getParameter("username");
		String password = req.getParameter("pswd");
		//��װΪUser���󣬲�ʹ��UserService���в�ѯ������ֵ���ݸ�ԭUser����
		User uTemp = new User(null, username, password);
		uTemp = us.login(uTemp);
		if(null==uTemp){
			//������ؿյ�User�����ʾ�����ݿ��в�ѯ����������û���������ļ�¼�����û���/�������
			//�ض�����ҳ�����û�������¼
			System.out.println("��¼ʧ��...�û�����" + username + "...���룺" + password + "...");
			resp.sendRedirect(req.getContextPath() + "/index.jsp?error=1");
		} else {
			//����Ϊ�գ����û�����������ȷ���ض�����ҳ���Servlet
			//Ҫ���н�һ����ѯ������Щ�˵����ٽ����������JSP��ʾ
			req.getSession().setAttribute("user", uTemp);
			resp.sendRedirect(req.getContextPath() + "/mainServlet");
		}
	}

}
