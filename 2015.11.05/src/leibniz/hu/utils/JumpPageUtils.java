package leibniz.hu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Leibniz
 * 用于从properties文件中读取转发/重定向页面的一个工具类
 */
public class JumpPageUtils {
	
	private static Properties prop;
	
	/**
	 * @param key
	 * @return
	 * 根据key值读取页面地址
	 */
	public static String getPage(String key) {
		return prop.getProperty(key);
	}

	/**
	 * 提供一个设置properties文件读取输入流的方法
	 * 在此方法中直接读取文件到properties对象中，不作保存
	 */
	public static void setPropFile(InputStream in){
		prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
