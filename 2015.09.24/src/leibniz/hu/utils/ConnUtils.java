package leibniz.hu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author Leibniz
 * 这个类用于维护一个数据库连接池
 * 其中静态代码块用于生成数据库连接池，及close()方法的处理
 * getConn()方法用于获取一个数据库连接
 * 数据库连接的参数可以在jdbc.properties中设置
 */
public class ConnUtils {
	//连接池，使用链表维护，更加快速
	private static LinkedList<Connection> connPool = new LinkedList<Connection>();
	
	//静态代码块，加载类时加载连接，添加到连接池
	static{
		try{
			//从properties文件读取数据库配置
			Properties prop = new Properties();
			String path = ConnUtils.class.getClassLoader().getResource("jdbc.properties").getPath();
			path = URLDecoder.decode(path,"UTF-8");
			prop.load(new FileInputStream(new File(path)));
			//从配置文件获取各个参数
			String driver = prop.getProperty("driver");
			String mysqlUrl = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pswd = prop.getProperty("pswd");
			String poolSize = prop.getProperty("poolSize");
			//创建connection对象并加入到连接池
			Class.forName(driver);
			for(int i = 0; i < Integer.parseInt(poolSize); i++){
				final Connection conn = DriverManager.getConnection(mysqlUrl, user, pswd);
				//对连接进行代理
				Connection connProxy = 
						(Connection)Proxy.newProxyInstance(ConnUtils.class.getClassLoader(), 
															new Class[]{Connection.class}, 
															new InvocationHandler(){
					@Override
					public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {
						//调用close()时，回收资源到连接池，并唤醒其他/一个线程
						if("close".equals(method.getName())){
							System.out.println("释放一个连接...");
							synchronized (connPool) {
								connPool.add((Connection) proxy);
								connPool.notify();//或者connPool.notifyAll();
							}
							return null;
						}
						return method.invoke(conn, args);
					}
				});
				//将代理后的代理对象放入连接池
				connPool.add(connProxy);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			
		}		
	}
	
	/**
	 * 从连接池中获取一个连接并返回
	 * @return
	 */
	public static Connection getConn(){
		synchronized (connPool) {
			//如果连接池空，则一直等待其他线程释放连接时唤醒
			if (connPool.size() == 0) {
				try {
					connPool.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return getConn();
			}
			//获取一个连接并返回
			Connection conn = connPool.removeFirst();
			System.err.println("取出前连接池中还剩:" + connPool.size() + "个连接...");
			return conn;//使用链表额removeFirst()方法，效率更高
		}
	}
}
