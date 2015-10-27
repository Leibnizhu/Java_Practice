package leibniz.hu.utils;

import java.util.UUID;

public class CommonUtil {
	public static String getUUIDString(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
