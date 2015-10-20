package leibniz.hu.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author Leibniz
 * ����Session��Attribute
 * ���û���¼ʱ���Ὣuser����Session�У���ʱӦ�ý�Session������ά����һ��Map�У���SessionIdΪ��
 * ��Map�洢����Ϊ�˷�����ң���߲���Ч��;��Ϊ��ȫվ����������û����ݣ��������MapҪ�洢��context��
 * ���û��˳���¼��Ӧ��Session�е�user����ɾ������ʱӦ�ý���Map�Ķ�Ӧ��ֵ��ɾ��
 */
public class SessAttrListener implements HttpSessionAttributeListener {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
	 * ���û���¼ʱ���Ὣuser����Session�У���ʱӦ�ý�Session������ά����һ��Map�У���SessionIdΪ��
	 * ��Map�洢����Ϊ�˷�����ң���߲���Ч��;��Ϊ��ȫվ����������û����ݣ��������MapҪ�洢��context��
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		String key = se.getName();
		//���������user����
		if("user".equals(key)){
			//��ȡ��ĿContext�����ڴ洢�����û����ݵ�Map
			ServletContext sc = se.getSession().getServletContext();
			Map<String, HttpSession> online = (Map<String, HttpSession>) sc.getAttribute("online");
			
			//����ǵ�һ���˵�¼��Ӧ���½�һ��Map��������Context��
			if(null == online){
				online = new HashMap<String, HttpSession>();
				sc.setAttribute("online", online);
			}
			
			//��󽫵�ǰSession�����Map
			HttpSession session = se.getSession();
			online.put(session.getId(), session);
			System.out.println(online);
			
			//��¼��¼����
			Integer logged = (Integer) sc.getAttribute("loggedCnt");
			if( logged == null){
				sc.setAttribute("loggedCnt", new Integer(1));
			} else {
				sc.setAttribute("loggedCnt", logged + 1);
			}
		}

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
	 * ���û��˳���¼��Ӧ��Session�е�user����ɾ������ʱӦ�ý���Map�Ķ�Ӧ��ֵ��ɾ��
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String key = se.getName();
		//�����ɾ��user����
		if("user".equals(key)){
			//��ȡ��ĿContext�����ڴ洢�����û����ݵ�Map
			ServletContext sc = se.getSession().getServletContext();
			Map<String, HttpSession> online = (Map<String, HttpSession>) sc.getAttribute("online");
			online.remove(se.getSession().getId());
			System.out.println(online);
			//�����ѵ�¼������¼
			Integer logged = (Integer) sc.getAttribute("loggedCnt");
			sc.setAttribute("loggedCnt", logged - 1);
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

}
