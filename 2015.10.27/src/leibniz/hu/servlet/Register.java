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
		//��ȡ���ֲ�������User����
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String id = CommonUtil.getUUIDString();
		String activeCode = CommonUtil.getUUIDString() + CommonUtil.getUUIDString();
		User user = new User(id, name, pwd, email, activeCode);
		//��Ϊ��ĿС��ʡȥService��DAO��ֱ�Ӳ������ݿ⣬�ж��û��Ƿ���ڣ�д�����û�����
		Connection conn = null;
		try{
			conn = DataSourceUtil.getDataSource().getConnection();
			//��ʼ����
			conn.setAutoCommit(false);
			QueryRunner qRun = new QueryRunner();
			//�Ȳ�ѯ�û����Ƿ����
			String sql = "select * from users where name = ?";
			User uQuery = qRun.query(conn, sql, new BeanHandler<User>(User.class), name);
			if(uQuery == null){
				//�û���Ϣ���浽User
				sql = "insert into users values(?,?,?,?)";
				qRun.update(conn, sql, user.getId(), user.getName(), user.getPwd(), user.getEmail());
				//�����뱣�浽active��
				sql = "insert into active values(?,?)";
				qRun.update(conn, sql, user.getId(), user.getCode());
				//�ύ
				conn.commit();
			} else {
				//�û����Ѵ��ڣ�����ע��
				response.sendRedirect(request.getContextPath() + "/index.jsp?error=1");
			}
		} catch(Exception e){
			try {
				//�׳��쳣��ع�
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e);
		} finally{
			try {
				//������ζ�Ҫ�ر�����
				conn.close();
				DataSourceUtil.remove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("���Ѿ�ע��ɹ�����ȥ�����δ�յ��ʼ������Ժ��ٲ����ʼ�.");
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
					return new PasswordAuthentication("*******", "*****");//�����ҵ�IP�ѱ�126�������������
				}
			});
			sess.setDebug(true);
			MimeMessage mm= new MimeMessage(sess);
			mm.setFrom(new InternetAddress("Leibniz.hu@leibniz.net"));
			mm.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			mm.setSubject("�뼤�������˻�");
			String url = "http://192.168.0.11:8080/activeCode/activation?id=" + user.getCode();
			String text = "���ã�" + user.getName() + ":<br/>��<a href='" + url + "'/>����˴�����</a>�� " +
							"��Ҳ���Ը������µ�ַ���������ַ����<br/>" + url;
			mm.setContent(text, "text/html;charset=UTf-8");
			
			Transport.send(mm);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}