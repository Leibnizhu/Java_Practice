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
		//����index.jsp������ת������Ϊ�˳����¼�ɹ���
		//��Ҫ��������Cookie�����л�ȡ�Ƿ��¼��
		if(req.getSession().getAttribute("exit")==null){
			//���Ǵ��˳���¼�ض������
			if(req.getSession().getAttribute("name")==null){
				//Session����name��֤��δ��¼����Ҫ����Cookie
				if(!uri.contains("/login")){
					//����login���servletʱҲ�������Cookie
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
				}
			}
		}else{
			//Session�а���exit���ԣ�˵���Ǹ��˳���¼�ض�������ģ�Ӧ��ɾ�������ԣ��Ա���һ�ν���ʱִ���Զ���¼
			req.getSession().removeAttribute("exit");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
