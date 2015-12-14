package leibniz.hu.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import leibniz.hu.dao.UserDAO;
import leibniz.hu.domain.User;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 4972400478033071868L;

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
}
