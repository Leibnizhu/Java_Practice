package leibniz.hu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {
	/**
	 * @return
	 * 获取一个32的UUID字符串
	 */
	public static String getUUIDString(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * @param src
	 * @return
	 * 获取src的MD5码字符串
	 */
	public static String getMD5(String src){
		String cipher = "";
		try {
			//先得到加密后的byte[]
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buf = md.digest(src.getBytes());
			//将byte[]解析成十六进制字符串
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
	 * 获取指定格式的日期时间字符串
	 */
	public static String getDateTime(){
		return sdf.format(new Date());
	}
	
	/**
	 * @return
	 * 生成10位数字的订单号
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
