package leibniz.hu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import leibniz.hu.domain.User;
import leibniz.hu.utils.CommonUtil;
import leibniz.hu.utils.DataSourceUtil;

public class Register extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1768281097167939653L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取各种参数构造User对象
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String id = CommonUtil.getUUIDString();
		String activeCode = CommonUtil.getUUIDString() + CommonUtil.getUUIDString();
		User user = new User(id, name, pwd, email, activeCode);
		//因为项目小，省去Service和DAO，直接操作数据库，判断用户是否存在，写入新用户数据
		Connection conn = null;
		try{
			conn = DataSourceUtil.getDataSource().getConnection();
			//开始事务
			conn.setAutoCommit(false);
			QueryRunner qRun = new QueryRunner();
			//先查询用户名是否存在
			String sql = "select * from users where name = ?";
			User uQuery = qRun.query(conn, sql, new BeanHandler<User>(User.class), name);
			if(uQuery == null){
				//用户信息保存到User
				sql = "insert into users values(?,?,?,?)";
				qRun.update(conn, sql, user.getId(), user.getName(), user.getPwd(), user.getEmail());
				//激活码保存到active表
				sql = "insert into active values(?,?)";
				qRun.update(conn, sql, user.getId(), user.getCode());
				//提交
				conn.commit();
			} else {
				//用户名已存在，不能注册
				response.sendRedirect(request.getContextPath() + "/index.jsp?error=1");
			}
		} catch(Exception e){
			try {
				//抛出异常则回滚
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally{
			try {
				//不管如何都要关闭连接
				conn.close();
				DataSourceUtil.remove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("您已经注册成功，请去激活，若未收到邮件，请稍后再查收邮件.");
		new SendEmailTheard(user).start();
	}
}

class SendEmailTheard extends Thread{
	private User user;
	public SendEmailTheard(User user){
		this.user = user;
	}
	@Override
	public void run() {
		try{
			Properties prop = new Properties();
			prop.setProperty("mail.host", "smtp.126.com");
			prop.setProperty("mail.smtp.auth", "true");
			Session sess = Session.getDefaultInstance(prop, new Authenticator(){
				public PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication("*******", "*****");//发现我的IP已被126列入黑名单，哭
				}
			});
			sess.setDebug(true);
			MimeMessage mm= new MimeMessage(sess);
			mm.setFrom(new InternetAddress("Leibniz.hu@leibniz.net"));
			mm.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			mm.setSubject("请激活您的账户");
			String url = "http://192.168.0.11:8080/activeCode/activation?id=" + user.getCode();
			String text = "您好，" + user.getName() + ":<br/>请<a href='" + url + "'/>点击此处激活</a>， " +
							"你也可以复制以下地址到浏览器地址栏：<br/>" + url;
			mm.setContent(text, "text/html;charset=UTf-8");
			
			Transport.send(mm);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}