package leibniz.hu.action;

import org.apache.struts2.ServletActionContext;

import leibniz.hu.dao.UserDAO;
import leibniz.hu.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User>{

	private static final long serialVersionUID = -7641516623271305156L;

	User user = new User();

	@Override
	public User getModel() {
		return user;
	}
	
	public String login(){
		System.out.println("loginAction---login()");
		//调用DAO查询是否有此用户/密码
		UserDAO userDao = new UserDAO();
		User newUser = userDao.login(user);
		//如果不存在用户/密码则重新输入
		if(null == newUser){
			this.addFieldError("error", "请输入正确用户名和密码!!!");
			return "error";
		}
		//将User放入Session
		ServletActionContext.getContext().getSession().put("user", newUser);
		return "success";
	}
}
