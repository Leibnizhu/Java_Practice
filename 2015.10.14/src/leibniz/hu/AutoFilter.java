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
		//当打开index.jsp，非跳转到（因为退出或登录成功）
		//需要遍历所有Cookie，从中获取是否登录过
		if(req.getSession().getAttribute("exit")==null){
			//并非从退出登录重定向而来
			if(req.getSession().getAttribute("name")==null){
				//Session中无name，证明未登录，需要遍历Cookie
				if(!uri.contains("/login")){
					//请求login这个servlet时也无需遍历Cookie
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
				}
			}
		}else{
			//Session中包含exit属性，说明是刚退出登录重定向过来的，应该删除该属性，以便下一次进入时执行自动登录
			req.getSession().removeAttribute("exit");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
