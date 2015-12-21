package leibniz.hu.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import leibniz.hu.dao.UserDAO;
import leibniz.hu.domain.User;
import leibniz.hu.utils.CommonUtil;

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

	@Override
	public User getModel() {
		return user;
	}
	
	public String list(){
		System.out.println("UserAction---list()");
		UserDAO userDao = new UserDAO();
		List<User> userList = userDao.list();
		if(null == userList){
			//鏌ヨ澶辫触鐨勫鐞�
		}
//		System.out.println(userList);
		ServletActionContext.getRequest().setAttribute("userList", userList);
		return "list";
	}
	public String select(){
		System.out.println("UserAction---select()");
		UserDAO userDao = new UserDAO();
		List<User> userList = userDao.select(user);
		//System.out.println(user);
		if(null == userList){
			//鏌ヨ澶辫触鐨勫鐞�
		}
		ServletActionContext.getRequest().setAttribute("userList", userList);
		return "list";
	}
	
	public String add(){
		System.out.println("UserAction---add()");
		UserDAO userDao = new UserDAO();
		System.out.println(user);	 
		//为了避免不同用户之间的简历 重名，用userID来保存，在数据库中保存简历的原名，用于下载和显示；
		String userID = CommonUtil.getUUIDString();
		user.setUserID(userID);
		user.setResume("0");
		//判断文件类型不为空，且文件名那有内容
		if(null != user.getUploadContentType() && user.getUploadFileName().trim() != ""){
			String originFileName = user.getUploadFileName();
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			String path = ServletActionContext.getServletContext().getRealPath("upload");
			System.out.println(originFileName+" "+extension + " " + path);
			
			File file = new File(path, userID + extension);
			try {
				//将上传的临时文件复制到指定的上传文件夹，并删除临时文件
				FileUtils.copyFile(user.getUpload(), file);
				user.getUpload().delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
			user.setFilename(originFileName);
			user.setResume("1");
		}
		userDao.add(user);
		//增加用户之后
		list();
		return "list";
	}
}
