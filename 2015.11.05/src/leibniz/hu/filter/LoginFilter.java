package leibniz.hu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		if(null == hreq.getSession().getAttribute("user")){
			//���session��û��user���ԣ�֤��û��¼
			if(hreq.getRequestURI().contains("buyServlet")){
				//����ǵ������ģ�Ӧ�ñ��浱ǰ���鼮id����½��ָ����鼮������ҳ��
				hreq.getSession().setAttribute("bookid", hreq.getParameter("bookid"));
			}
			//Ӧ��ת����¼ҳ��
			hreq.getSession().setAttribute("errorMsg", "���ȵ�¼...");
			((HttpServletResponse)response).sendRedirect(hreq.getContextPath() + "/jsp/login.jsp");
		} else{
			//�����user���ԣ��ѵ�¼�������
			chain.doFilter(hreq, response);
		}
	}

	@Override
	public void destroy() {

	}

}
