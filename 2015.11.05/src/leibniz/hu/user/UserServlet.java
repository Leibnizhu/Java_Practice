package leibniz.hu.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import leibniz.hu.utils.BaseServlet;
import leibniz.hu.utils.CommonUtil;

public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = -3493735855938437156L;
	private UserService userServ = new UserService();
	
	/* (non-Javadoc)
	 * @see leibniz.hu.utils.BaseServlet#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * �����û�ע��
	 */
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		//Get user input check code
		String userCode = req.getParameter("checkcode");
		//Get local check code from session
		HttpSession sess = req.getSession();
		String localCode = (String) sess.getAttribute("CheckCode");
		//System.out.println(localCode);
		if(localCode == null){
			req.getSession().setAttribute("errorMsg", "��֤����ڡ�����");
			//�������û�ע��
			return "reg";
		} else {
			if(localCode.equalsIgnoreCase(userCode)){
				//System.out.println("Check code right!!!");
				//��֤����ȷ����ʼע���û�
				//�Ȳ�ѯ�ж��Ƿ��û����Ѵ���
				String name = req.getParameter("name");
				String pswd = req.getParameter("pswd");
				String email = req.getParameter("email");
				User rUser = userServ.queryByName(name);
				if(null != rUser){
					//�û��Ѵ��ڣ�����ע��
					req.getSession().setAttribute("errorMsg", "�û����Ѵ��ڣ��뻻һ��������");
					//�������û�ע�ᣬ��ת��ע��ҳ�������������֤�룬����ɾ��session�е�CheckCode����
					return "reg";
				} else {
					//�û��������ڣ����Դ����µ��û�
					User newUser = new User(CommonUtil.getUUIDString(), name, CommonUtil.getMD5(name + pswd), email);
					userServ.register(newUser);
					req.getSession().setAttribute("errorMsg", "ע��ɹ������¼������");
					//Clear check code session
					sess.removeAttribute("CheckCode");
					//�������û���¼
					return "login";
				}
			} else {
				//System.out.println("Check code WRONG!!!");
				req.getSession().setAttribute("errorMsg", "��֤����󡣡���");
				//�������û�ע��
				return "reg";
			}
		}
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * �����û���¼
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//Map map = req.getParameterMap();
		//BeanUtils.populate(user, map);
		String pswd = CommonUtil.getMD5(req.getParameter("name") + req.getParameter("pswd"));
		User user = new User(null, req.getParameter("name"), pswd, null);
		User rUser = userServ.login(user);
		if(null == rUser){
			//���ݿ��в��Ҳ������û�������null
			//��User�Ż�request����login.jsp���û����ı�������ʾ֮ǰ������û������������
			//ֻ�ܷ���request��ת���������ܷ���session�У�������ж�Ϊ��¼�ɹ�
			req.setAttribute("user", user);
			//��session�з��������Ϣ
			req.getSession().setAttribute("errorMsg", "�û����������������������");
			//�������û���¼
			return "login";
		} else {
			//��¼�ɹ�����user����session��
			req.getSession().setAttribute("user", rUser);
			if(null != req.getSession().getAttribute("bookid")){
				//���Session����bookid���ԣ�˵���Ǵ��鼮����ҳ������ģ�Ӧ�÷��ص��鼮����ҳ��
				req.getRequestDispatcher("/bookServlet?cmd=detail&bookid=" + req.getSession().getAttribute("bookid")).forward(req, resp);
				return null;
			}
			//����ת���ɹ�ҳ��	
			return "logsuss";
		}
	}
	
	public String register(HttpServletRequest req, HttpServletResponse resp){
		return execute(req, resp);
	}
	
	public String logout(HttpServletRequest req, HttpServletResponse resp){
		req.getSession().removeAttribute("user");
		return "in";
	}
}
