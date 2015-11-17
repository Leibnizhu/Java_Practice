package leibniz.hu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {
	/**
	 * @return
	 * ��ȡһ��32��UUID�ַ���
	 */
	public static String getUUIDString(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * @param src
	 * @return
	 * ��ȡsrc��MD5���ַ���
	 */
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
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @return
	 * ��ȡָ����ʽ������ʱ���ַ���
	 */
	public static String getDateTime(){
		return sdf.format(new Date());
	}
	
	/**
	 * @return
	 * ����10λ���ֵĶ�����
	 */
	public static String getOrderId() {
		 byte[] buf = getMD5(getDateTime()).getBytes();
		 StringBuffer strBuf = new StringBuffer();
		 for(int i = 0; i < 10; i++){
			 strBuf.append(String.valueOf(( buf[i*3] + buf[i*3+1]+ buf[i*3+2] )%10) );
		 }
		 //System.out.println(strBuf.toString());
		 return strBuf.toString();
	}
}
