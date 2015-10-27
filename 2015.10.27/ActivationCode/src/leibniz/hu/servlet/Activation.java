package leibniz.hu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import leibniz.hu.utils.DataSourceUtil;

public class Activation extends HttpServlet {

	private static final long serialVersionUID = -6013429903031753803L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("id");
		try{
			QueryRunner qRun = new QueryRunner(DataSourceUtil.getDataSource());
			String sql = "delete from active where accode=?";
			int rows = qRun.update(sql, code);
			if(rows == 0){
				response.getWriter().print("激活码错误，激活失败。。。。");
			} else {
				response.getWriter().print("激活成功。。。。");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
