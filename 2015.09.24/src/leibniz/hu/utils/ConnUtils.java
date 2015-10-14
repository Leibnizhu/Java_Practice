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
 * ���������ά��һ�����ݿ����ӳ�
 * ���о�̬����������������ݿ����ӳأ���close()�����Ĵ���
 * getConn()�������ڻ�ȡһ�����ݿ�����
 * ���ݿ����ӵĲ���������jdbc.properties������
 */
public class ConnUtils {
	//���ӳأ�ʹ������ά�������ӿ���
	private static LinkedList<Connection> connPool = new LinkedList<Connection>();
	
	//��̬����飬������ʱ�������ӣ���ӵ����ӳ�
	static{
		try{
			//��properties�ļ���ȡ���ݿ�����
			Properties prop = new Properties();
			String path = ConnUtils.class.getClassLoader().getResource("jdbc.properties").getPath();
			path = URLDecoder.decode(path,"UTF-8");
			prop.load(new FileInputStream(new File(path)));
			//�������ļ���ȡ��������
			String driver = prop.getProperty("driver");
			String mysqlUrl = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pswd = prop.getProperty("pswd");
			String poolSize = prop.getProperty("poolSize");
			//����connection���󲢼��뵽���ӳ�
			Class.forName(driver);
			for(int i = 0; i < Integer.parseInt(poolSize); i++){
				final Connection conn = DriverManager.getConnection(mysqlUrl, user, pswd);
				//�����ӽ��д���
				Connection connProxy = 
						(Connection)Proxy.newProxyInstance(ConnUtils.class.getClassLoader(), 
															new Class[]{Connection.class}, 
															new InvocationHandler(){
					@Override
					public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {
						//����close()ʱ��������Դ�����ӳأ�����������/һ���߳�
						if("close".equals(method.getName())){
							System.out.println("�ͷ�һ������...");
							synchronized (connPool) {
								connPool.add((Connection) proxy);
								connPool.notify();//����connPool.notifyAll();
							}
							return null;
						}
						return method.invoke(conn, args);
					}
				});
				//�������Ĵ������������ӳ�
				connPool.add(connProxy);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			
		}		
	}
	
	/**
	 * �����ӳ��л�ȡһ�����Ӳ�����
	 * @return
	 */
	public static Connection getConn(){
		synchronized (connPool) {
			//������ӳؿգ���һֱ�ȴ������߳��ͷ�����ʱ����
			if (connPool.size() == 0) {
				try {
					connPool.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return getConn();
			}
			//��ȡһ�����Ӳ�����
			Connection conn = connPool.removeFirst();
			System.err.println("ȡ��ǰ���ӳ��л�ʣ:" + connPool.size() + "������...");
			return conn;//ʹ�������removeFirst()������Ч�ʸ���
		}
	}
}
