package leibniz.hu.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import leibniz.hu.utils.JumpPageUtils;

public class JumpPageConfigListener implements ServletContextListener  {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * ������ServletContexts��ʼ����ʱ�򣬽�web.xml�����õ�jumpConfig�����properties�ļ�����
	 * ���뵽JumpPageUtils�������н��ж�ȡ
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext svCtx = sce.getServletContext();
		String jumpConfig = svCtx.getInitParameter("jumpConfig");
		if(null == jumpConfig){
			//����û�û����xml�����ã������Ĭ�ϵ�һ��properties
			jumpConfig = "jumpConfig.properties";
		}
		
		System.out.println(">>>>>>>>>" + jumpConfig);
		InputStream in = null;
		if(jumpConfig.startsWith("/")){
			//�ļ�����/��ͷ����Ҫ��WebRoot�ж�ȡ
			String path = svCtx.getRealPath(jumpConfig);
			try {
				in = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			//�����Ե�ǰ������������Ŀ¼��ȡ����src�����ӦWEB-INF\classes\
			in = JumpPageConfigListener.class.getClassLoader().getResourceAsStream(jumpConfig);
		}
		JumpPageUtils.setPropFile(in);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
