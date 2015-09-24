package leibniz.hu.utils;

/**
 * @author Leibniz
 * 该类目前只有一个静态方法，为users和contacts表提供UUID作为其id
 */
public class UUID {
	public static String getUUID(){
		return java.util.UUID.randomUUID().toString().replace("-","");
	}
}
