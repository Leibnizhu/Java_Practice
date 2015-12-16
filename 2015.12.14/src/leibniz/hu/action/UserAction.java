package leibniz.hu.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import leibniz.hu.dao.UserDAO;
import leibniz.hu.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private static final long serialVersionUID = 4972400478033071868L;
	private User user=new User();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String list(){
		System.out.println("UserAction---list()");
		UserDAO userDao = new UserDAO();
		List<User> userList = userDao.list();
		if(null == userList){
			//查询失败的处理
		}
		ServletActionContext.getRequest().setAttribute("userList", userList);
		return "list";
	}
	public String select(){
		System.out.println("UserAction---select()");
		UserDAO userDao = new UserDAO();
		List<User> userList = userDao.select(user);
		//System.out.println(user);
		if(null == userList){
			//查询失败的处理
		}
		ServletActionContext.getRequest().setAttribute("userList", userList);
		return "list";
	}
	@Override
	public User getModel() {
		return user;
	}
}
