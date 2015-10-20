package leibniz.hu.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//��¼��������
		ServletContext sc = se.getSession().getServletContext();
		Integer connected = (Integer) sc.getAttribute("connectedCnt");
		if( connected == null){
			sc.setAttribute("connectedCnt", new Integer(1));
		} else {
			sc.setAttribute("connectedCnt", ++connected);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//�޸���������
		ServletContext sc = se.getSession().getServletContext();
		Integer connected = (Integer) sc.getAttribute("connectedCnt");
		sc.setAttribute("connectedCnt", --connected);
	}

}
