package leibniz.hu;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AutoFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		//遍历所有Cookie，从中获取是否登录过
		Cookie[] cs = req.getCookies();
		if(cs != null){
			for(Cookie c: cs){
				if(c.getName().equals("autoLogin")){
					String name = URLDecoder.decode(c.getValue(), "UTF-8");
					//Cookie中有登录信息，加入Session中，并无需继续进行循环
					req.getSession().setAttribute("name", name);
					break;
				}
			}
		} 
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
