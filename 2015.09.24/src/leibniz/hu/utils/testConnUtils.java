package leibniz.hu.utils;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class testConnUtils {

	public static void main(String[] args){
		
		for(int i = 0; i < 20; i++){
			new ConnThread().start();
		}
	}
	
	@Test
	public void testConnUtil(){
		for(int i = 0; i < 3; i++){
			new ConnThread().start();
		}
	}
}

class ConnThread extends Thread{

	@Override
	public void run() {
		Connection conn = null;
		try {
			conn = ConnUtils.getConn();
			//开始事务
			conn.setAutoCommit(false);
			Statement stat = conn.createStatement();
			stat.execute("insert into users values('" + this.getName() + "', 'Jucy" + this.getName() + "', '28')");//以当前进程名为id写入一个记录
			conn.commit();//提交事务
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}