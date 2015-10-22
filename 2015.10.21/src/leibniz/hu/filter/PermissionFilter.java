package leibniz.hu.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import leibniz.hu.domain.User;
import leibniz.hu.utils.DataSourceUtil;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class PermissionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//��ȡSession�е�user����
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");
		//��ȡ���ʵĵ�ַ����ȥ��ͷ��������SQL��ѯ
		String url = req.getRequestURI();
		url = url.replace(req.getContextPath(), "");
		//��ѯ���ݿ⣬���û��Ƿ�ӵ�з��ʸ�ҳ���Ȩ��
		String sql = "SELECT COUNT(1) from menus m " + 
					"INNER JOIN rolemenu rm on m.id=rm.mid " +
					"INNER JOIN roles r ON r.id=rm.rid " +
					"INNER JOIN userrole ur ON ur.rid=r.id " +
					"WHERE url=? AND ur.uid=?";
		QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
		try {
			Object o = qRun.query(sql, new ScalarHandler(), url, user.getId());
			int count = Integer.parseInt(o.toString());
			if(0 == count){
				System.out.println("-------->>������ͼ����û��Ȩ�޵���ҳ");
				//req.getSession().setAttribute("error", "�޸�ҳ�ķ���Ȩ��");
				try {
					((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/index.jsp?error=3");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				chain.doFilter(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() {
	}

}
