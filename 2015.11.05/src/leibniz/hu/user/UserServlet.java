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
	 * 用于用户注册
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
			req.getSession().setAttribute("errorMsg", "验证码过期。。。");
			//继续让用户注册
			return "reg";
		} else {
			if(localCode.equalsIgnoreCase(userCode)){
				//System.out.println("Check code right!!!");
				//验证码正确，开始注册用户
				//先查询判断是否用户名已存在
				String name = req.getParameter("name");
				String pswd = req.getParameter("pswd");
				String email = req.getParameter("email");
				User rUser = userServ.queryByName(name);
				if(null != rUser){
					//用户已存在，不能注册
					req.getSession().setAttribute("errorMsg", "用户名已存在，请换一个。。。");
					//继续让用户注册，跳转到注册页面会重新请求验证码，无需删除session中的CheckCode属性
					return "reg";
				} else {
					//用户名不存在，可以创建新的用户
					User newUser = new User(CommonUtil.getUUIDString(), name, CommonUtil.getMD5(name + pswd), email);
					userServ.register(newUser);
					req.getSession().setAttribute("errorMsg", "注册成功，请登录。。。");
					//Clear check code session
					sess.removeAttribute("CheckCode");
					//继续让用户登录
					return "login";
				}
			} else {
				//System.out.println("Check code WRONG!!!");
				req.getSession().setAttribute("errorMsg", "验证码错误。。。");
				//继续让用户注册
				return "reg";
			}
		}
	}

	/**
	 * @param req
	 * @param resp
	 * @return
	 * 用于用户登录
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//Map map = req.getParameterMap();
		//BeanUtils.populate(user, map);
		String pswd = CommonUtil.getMD5(req.getParameter("name") + req.getParameter("pswd"));
		User user = new User(null, req.getParameter("name"), pswd, null);
		User rUser = userServ.login(user);
		if(null == rUser){
			//数据库中查找不到该用户，返回null
			//将User放回request，在login.jsp的用户名文本框中显示之前输入的用户名，方便操作
			//只能放在request中转发，而不能放在session中，否则会判断为登录成功
			req.setAttribute("user", user);
			//在session中放入错误信息
			req.getSession().setAttribute("errorMsg", "用户名或密码错误，请重新输入");
			//继续让用户登录
			return "login";
		} else {
			//登录成功，将user放入session中
			req.getSession().setAttribute("user", rUser);
			if(null != req.getSession().getAttribute("bookid")){
				//如果Session中有bookid属性，说明是从书籍详情页面进来的，应该返回到书籍详情页面
				req.getRequestDispatcher("/bookServlet?cmd=detail&bookid=" + req.getSession().getAttribute("bookid")).forward(req, resp);
				return null;
			}
			//并跳转到成功页面	
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
