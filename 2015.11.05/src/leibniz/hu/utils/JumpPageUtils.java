package leibniz.hu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Leibniz
 * ���ڴ�properties�ļ��ж�ȡת��/�ض���ҳ���һ��������
 */
public class JumpPageUtils {
	
	private static Properties prop;
	
	/**
	 * @param key
	 * @return
	 * ����keyֵ��ȡҳ���ַ
	 */
	public static String getPage(String key) {
		return prop.getProperty(key);
	}

	/**
	 * �ṩһ������properties�ļ���ȡ�������ķ���
	 * �ڴ˷�����ֱ�Ӷ�ȡ�ļ���properties�����У���������
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
