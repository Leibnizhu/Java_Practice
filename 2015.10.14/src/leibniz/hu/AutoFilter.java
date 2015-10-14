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
		//��������Cookie�����л�ȡ�Ƿ��¼��
		Cookie[] cs = req.getCookies();
		if(cs != null){
			for(Cookie c: cs){
				if(c.getName().equals("autoLogin")){
					String name = URLDecoder.decode(c.getValue(), "UTF-8");
					//Cookie���е�¼��Ϣ������Session�У��������������ѭ��
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
