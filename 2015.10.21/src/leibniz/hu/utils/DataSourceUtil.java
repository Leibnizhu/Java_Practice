package leibniz.hu.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Leibniz
 * ���ݿ���صĹ����࣬ȫ����̬
 */
public class DataSourceUtil {
	//DataSource���ӳر���
	private static DataSource ds;
	//����ThreadLocalģʽ����֤ͬһ���߳̿���ʹ��ͬһ��Connection����ǿ���ݿ�����İ�ȫ�Ժ�������
	private static ThreadLocal<Connection> tlConn = new ThreadLocal<Connection>();
	//ʹ�þ�̬������ȡc3p0������
	static{
		ds = new ComboPooledDataSource("leibniz");
	}
	
	//�ṩ��ȡDataSource�ķ���
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * @return Connection
	 * �ṩ�����ӳػ�ȡ���ӵķ���
	 * ������Ҫʹ�����񣬵��ö��DAO�������ģ�Ӧ��ʹ�ø�����
	 */
	public static Connection getConnTrans(){
		Connection conn = tlConn.get();
		if(null == conn){
			try {
				//���ThreadLocal��û��Connection��������ӳ��л�ȡһ��������ThreadLocal��
				conn = ds.getConnection();
				tlConn.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * @return Connection
	 * ���ڲ���Ҫʹ������������Ӧʹ�����������ֱ�Ӵ����ӳ��л�ȡ����
	 * ���ܱ�֤ͬһ�߳��õ�ͬһ������
	 */
	public static Connection getConnNonTrans(){
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return conn;
	}
	
	/**
	 * ��ThreadLocal���Ƴ�����
	 */
	public static void remove(){
		tlConn.remove();
	}
}
