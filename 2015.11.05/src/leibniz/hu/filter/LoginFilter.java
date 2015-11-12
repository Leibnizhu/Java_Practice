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
			//如果session中没有user属性，证明没登录
			if(hreq.getRequestURI().contains("buyServlet")){
				//如果是点击购买的，应该保存当前的书籍id，登陆后恢复该书籍的详情页面
				hreq.getSession().setAttribute("bookid", hreq.getParameter("bookid"));
			}
			//应跳转到登录页面
			hreq.getSession().setAttribute("errorMsg", "请先登录...");
			((HttpServletResponse)response).sendRedirect(hreq.getContextPath() + "/jsp/login.jsp");
		} else{
			//如果有user属性，已登录，则放行
			chain.doFilter(hreq, response);
		}
	}

	@Override
	public void destroy() {

	}

}
