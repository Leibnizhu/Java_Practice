package leibniz.hu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
		list();
		return "list";
	}
	
	/**
	 * 根据指定ID显示某个用户的详细信息
	 * @return
	 */
	public String view(){
		System.out.println("UserAction---view()");
		UserDAO userDao = new UserDAO();
		User user = userDao.getUserById(ServletActionContext.getRequest().getParameter("userID"));
		//将新的用户user放入值栈，以便回显
		ValueStack vs = ServletActionContext.getContext().getValueStack();
		vs.pop();
		vs.push(user);
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
		User user = userDao.getUserById(userID);
		//判断用户存在
		if(null != user){
			//判断该用户有简历
			if("1".equals(user.getResume())){
				//获取原文件名及后缀
				String originFilename = user.getFilename();
				String extension = originFilename.substring(originFilename.lastIndexOf("."));
				//拼接成保存上传文件的文件名
				String storedFilename = userID + extension;
				String storedPath = ServletActionContext.getServletContext().getRealPath("upload");
				//创建文件输入流
				try {
					FileInputStream fis = new FileInputStream(new File(storedPath, storedFilename));
					user.setDownloadStream(fis);
					//加入到值栈中取代原有的user对象
					ValueStack vs = ServletActionContext.getContext().getValueStack();
					vs.pop();
					vs.push(user);
					return "down";
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return "error";
	}
}
