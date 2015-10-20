package leibniz.hu.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author Leibniz
 * 监视Session的Attribute
 * 当用户登录时，会将user放入Session中，此时应该将Session放入所维护的一个Map中，以SessionId为键
 * 以Map存储，是为了方便查找，提高查找效率;因为是全站共享的在线用户数据，所以这个Map要存储在context中
 * 当用户退出登录，应将Session中的user属性删除，此时应该将该Map的对应键值对删除
 */
public class SessAttrListener implements HttpSessionAttributeListener {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 * 当用户登录时，会将user放入Session中，此时应该将Session放入所维护的一个Map中，以SessionId为键
	 * 以Map存储，是为了方便查找，提高查找效率;因为是全站共享的在线用户数据，所以这个Map要存储在context中
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		String key = se.getName();
		//如果是增加user属性
		if("user".equals(key)){
			//获取项目Context中用于存储在线用户数据的Map
			ServletContext sc = se.getSession().getServletContext();
			Map<String, HttpSession> online = (Map<String, HttpSession>) sc.getAttribute("online");
			
			//如果是第一个人登录，应该新建一个Map，并放在Context中
			if(null == online){
				online = new HashMap<String, HttpSession>();
				sc.setAttribute("online", online);
			}
			
			//最后将当前Session存入该Map
			HttpSession session = se.getSession();
			online.put(session.getId(), session);
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 * 当用户退出登录，应将Session中的user属性删除，此时应该将该Map的对应键值对删除
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String key = se.getName();
		//如果是删除user属性
		if("user".equals(key)){
			//获取项目Context中用于存储在线用户数据的Map
			ServletContext sc = se.getSession().getServletContext();
			Map<String, HttpSession> online = (Map<String, HttpSession>) sc.getAttribute("online");
			online.remove(se.getSession().getId());
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

}
