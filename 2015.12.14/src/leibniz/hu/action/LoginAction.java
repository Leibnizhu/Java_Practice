package leibniz.hu.action;

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
		return "success";
	}
}
