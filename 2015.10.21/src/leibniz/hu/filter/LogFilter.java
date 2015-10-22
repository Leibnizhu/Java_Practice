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

import leibniz.hu.domain.User;

public class LogFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");
		if(null == user){
			System.out.println("-------->>һ��δ��¼���û���ͼ�����ܱ�����JSPҳ��");
			//req.getSession().setAttribute("error", "���ȵ�¼");
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/index.jsp?error=2");
		} else {
			chain.doFilter(req, response);
		}
	}

	@Override
	public void destroy() {
	}

}
