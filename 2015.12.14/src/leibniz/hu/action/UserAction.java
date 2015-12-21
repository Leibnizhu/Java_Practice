package leibniz.hu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import leibniz.hu.dao.UserDAO;
import leibniz.hu.domain.User;
import leibniz.hu.utils.CommonUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

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
	
	/**
	 * 列出所有用户
	 * @return
	 */
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
	/**
	 * 根据页面返回的条件进行模糊查询
	 * @return
	 */
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
	
	/**
	 * 添加一个新的用户
	 * @return
	 */
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
		return "relist";
	}
	
	/**
	 * 根据指定ID显示某个用户的详细信息
	 * @return
	 */
	public String view(){
		System.out.println("UserAction---view()");
		UserDAO userDao = new UserDAO();
		User newuser = userDao.getUserById(ServletActionContext.getRequest().getParameter("userID"));
		//将新的用户user放入值栈，以便回显
		ValueStack vs = ServletActionContext.getContext().getValueStack();
		vs.pop();
		vs.push(newuser);
		return "view";
	}
	
	/**
	 * 下载用户的简历附件
	 * @return
	 */
	public String download(){
		System.out.println("UserAction---download()");
		//根据userID获取user属性（只是为了后缀名以及来判断是否有简历）
		String userID = ServletActionContext.getRequest().getParameter("userID");
		UserDAO userDao = new UserDAO();
		User newuser = userDao.getUserById(userID);
		//判断用户存在
		if(null != newuser){
			//判断该用户有简历
			if("1".equals(newuser.getResume())){
				//获取原文件名及后缀
				try {
					newuser.setFilename(new String(newuser.getFilename().getBytes("UTF-8"),"ISO8859-1"));
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				String originFilename = newuser.getFilename();
				String extension = originFilename.substring(originFilename.lastIndexOf("."));
				//拼接成保存上传文件的文件名
				String storedFilename = userID + extension;
				String storedPath = ServletActionContext.getServletContext().getRealPath("upload");
				//创建文件输入流
				try {
					FileInputStream fis = new FileInputStream(new File(storedPath, storedFilename));
					newuser.setDownloadStream(fis);
					//加入到值栈中取代原有的user对象
					ValueStack vs = ServletActionContext.getContext().getValueStack();
					vs.pop();
					vs.push(newuser);
					return "down";
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return "error";
	}
	
	/**
	 * 根据指定的userID删除用户
	 * @return
	 */
	public String delete(){
		System.out.println("UserAction---delete()");
		String userID = ServletActionContext.getRequest().getParameter("userID");
		//查询到userID，并删除对应的附件
		UserDAO userDao = new UserDAO();
		User newuser = userDao.getUserById(userID);
		//判断用户存在
		if(null != newuser){
			//判断该用户有简历
			if("1".equals(newuser.getResume())){
				//获取原文件名及后缀
				String originFilename = newuser.getFilename();
				String extension = originFilename.substring(originFilename.lastIndexOf("."));
				//拼接成保存上传文件的文件名
				String storedFilename = userID + extension;
				String storedPath = ServletActionContext.getServletContext().getRealPath("upload");
				new File(storedPath, storedFilename).delete();
			}
			userDao.deleteByID(userID);
		}
		return "relist";
	}
	
	/**
	 * 编辑用户，先查询数据库得到user，替代值栈，用于回显数据，并考虑处理兴趣爱好的字符串
	 * @return
	 */
	public String edit(){
		System.out.println("UserAction---edit()");
		String userID = ServletActionContext.getRequest().getParameter("userID");
		//查询到userID
		UserDAO userDao = new UserDAO();
		User newuser = userDao.getUserById(userID);
		//查询得到的user取代值栈中的user，以便回显
		ValueStack vs = ServletActionContext.getContext().getValueStack();
		vs.pop();
		vs.push(newuser);
		//处理兴趣爱好，原为逗号隔开的字符串
		String interest = newuser.getInterest();
		if(null != interest && !("".equals(interest.trim()))){
			//拆分成字符串数组并塞入request属性中
			String[] interests = interest.split(", ");
			ServletActionContext.getRequest().setAttribute("interests", interests);
		}
		return "edit";
	}
	
	/**
	 * 从编辑的jsp页面归来，将页面中的信息更新到数据库中
	 * @return
	 */
	public String update(){
		System.out.println("UserAction---update()");
		UserDAO userDao = new UserDAO();
		System.out.println(user);
		if(null != user.getUploadContentType() && user.getUploadFileName().trim() != ""){
			String originFileName = user.getUploadFileName();
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			String path = ServletActionContext.getServletContext().getRealPath("upload");
			File file = new File(path, user.getUserID() + extension);
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
		userDao.updateUser(user);
		return "relist";
	}
}
