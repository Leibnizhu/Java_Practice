package leibniz.hu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import leibniz.hu.domain.User;
import leibniz.hu.utils.DataSourceUtil;

public class Login extends HttpServlet {

	private static final long serialVersionUID = -8449422824200624279L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//��ȡ���ֲ�������User����
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		try{
			QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
			//ʹ�������ӣ���û�м�������û�Ҳ�����ѯ���
			String sql = "Select id,name,pwd,email,accode " +
						"from users left join active on users.id=active.uid " +
						"where name=? and pwd=?";
			User user = qRun.query(sql, new BeanHandler<User>(User.class), name, pwd);
			if(user == null){
				//��ѯ�������������û���������ļ�¼
				//�û������������
				response.sendRedirect(request.getContextPath() + "/index.jsp?error=2");
			} else {
				//�û�����������ȷ
				if(user.getCode() == null){
					//û�ж�Ӧ�ļ����룬֤���Ѽ���ɹ�
					System.out.println("��¼�ɹ�");
				} else{
					//�м����룬֤����û����
					response.sendRedirect(request.getContextPath() + "/index.jsp?error=3");
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
