package leibniz.hu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class CommonUtil {
	public static String getUUIDString(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getMD5(String src){
		String cipher = "";
		try {
			//�ȵõ����ܺ��byte[]
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buf = md.digest(src.getBytes());
			//��byte[]������ʮ�������ַ���
			for(byte b: buf){
				int bit = b & 0xFF;
				String hex = Integer.toHexString(bit);
				if(bit < 16){
					cipher =  cipher + "0" + hex;
				} else {
					cipher += hex;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return cipher;
	}
}
