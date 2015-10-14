package leibniz.hu.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.User;
import leibniz.hu.utils.DistributeServlet;

public class UserServlet extends DistributeServlet {

	private static final long serialVersionUID = 4230766353776938248L;
	private UserService us = new UserService();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		//�������н��ܲ�������װ��User����
		String name = req.getParameter("username");
		String pswd = req.getParameter("pswd");
		User user = new User(null, name, pswd);
		//����UserService�ķ�����¼
		user = us.login(user);
		//����null�򲻴��ڸ��û�
		if(user == null){
			System.err.println("�û���/�����������������...");
		} else {
			System.out.println("��¼�ɹ�...");
			//����ȷ���û�������뵽session�����У����ض���
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("/contacts/showContacts");
		}
	}
	
	public void logOut(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//TODO
	}

	public void register(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//TODO
	}
}
