package leibniz.hu.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Leibniz
 * 数据哭相关的工具类，全部静态
 */
public class DataSourceUtil {
	//DataSource连接池变量
	private static DataSource ds;
	//利用ThreadLocal模式，保证同一个线程可以使用同一个Connection，增强数据库事务的安全性和完整性
	private static ThreadLocal<Connection> tlConn = new ThreadLocal<Connection>();
	//使用静态代码块读取c3p0的配置
	static{
		ds = new ComboPooledDataSource("leibniz");
	}
	
	//提供获取DataSource的方法
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * @return Connection
	 * 提供从连接池获取连接的方法
	 * 对于需要使用事务，调用多个DAO完成事务的，应该使用该连接
	 */
	public static Connection getConnTrans(){
		Connection conn = tlConn.get();
		if(null == conn){
			try {
				//如果ThreadLocal中没有Connection，则从连接池中获取一个并放入ThreadLocal中
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
	 * 对于不需要使用事务的情况，应使用这个方法，直接从连接池中获取连接
	 * 不能保证同一线程拿到同一个连接
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
	 * 从ThreadLocal中移除连接
	 */
	public static void remove(){
		tlConn.remove();
	}
}
