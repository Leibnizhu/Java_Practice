package leibniz.hu.utils;

/**
 * @author Leibniz
 * ����Ŀǰֻ��һ����̬������Ϊusers��contacts���ṩUUID��Ϊ��id
 */
public class UUID {
	public static String getUUID(){
		return java.util.UUID.randomUUID().toString().replace("-","");
	}
}
