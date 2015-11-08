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
	 * 监听到ServletContexts初始化的时候，将web.xml中配置的jumpConfig代表的properties文件载入
	 * 放入到JumpPageUtils工具类中进行读取
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext svCtx = sce.getServletContext();
		String jumpConfig = svCtx.getInitParameter("jumpConfig");
		if(null == jumpConfig){
			//如果用户没有在xml中配置，则读入默认的一个properties
			jumpConfig = "jumpConfig.properties";
		}
		
		System.out.println(">>>>>>>>>" + jumpConfig);
		InputStream in = null;
		if(jumpConfig.startsWith("/")){
			//文件名以/开头，需要从WebRoot中读取
			String path = svCtx.getRealPath(jumpConfig);
			try {
				in = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			//否则以当前类的类加载器根目录读取，即src，或对应WEB-INF\classes\
			in = JumpPageConfigListener.class.getClassLoader().getResourceAsStream(jumpConfig);
		}
		JumpPageUtils.setPropFile(in);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
}
