import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.RandomAccess;

public class MultiThreadDown {
	public static void mtDown() throws Exception{
		//��ʼ�����ӵȹ���
		String path = "http://a.hiphotos.baidu.com/zhidao/pic/item/ca1349540923dd5446ea0302d009b3de9c82483a.jpg";
		String fileName = path.substring(path.lastIndexOf("/")+1);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.connect();
		//��ȡ״̬�벢��ʼ�����߳�
		int status = conn.getResponseCode();
		if(200 == status){
			//��ȡҪ���ص��ļ����ȣ���������ͬ��С�ı����ļ�
			int length = conn.getContentLength();
			RandomAccessFile raFile = new RandomAccessFile(new File("d:/" + fileName),"rw");
			raFile.setLength(length);
			raFile.close();
			//����ÿ���̵߳Ĳ���
			//���߳���
			int threadCnt = 5;
			int threadSize = length/threadCnt;
			for(int i = 0; i < threadCnt; i++){
				int start, end;
				start = i * threadSize;
				if(i != threadCnt -1 ){
					//ǰ����̶߳���threadSize����
					end = start + threadSize -1;
				} else {
					//���һ���߳�ֱ�Ӷ�����β��
					end = length-1;
				}
				new DownThread(url, "d:/" + fileName, start, end, length).start();
			}
		}
		conn.disconnect();
	}

	public static void main(String[] args) throws Exception {
		mtDown();
	}
	
}

class DownThread extends Thread{
	private URL url;
	private String fileName;
	private int start;
	private int end;
	private int length;
	private static Integer sum = 0;
	public DownThread(URL url, String fileName, int start, int end, int length) {
		this.url = url;
		this.fileName = fileName;
		this.start = start;
		this.end = end;
		this.length = length;
	}
	
	@Override
	public void run() {
		try{
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setRequestProperty("range", "bytes=" + start + "-" + end);
			System.out.println(start + ":" + end);
			conn.connect();
			int status = conn.getResponseCode();
			//�ϵ�����״̬��Ϊ206
			if(206 == status){
				int size = conn.getContentLength();
				InputStream in = conn.getInputStream();
				RandomAccessFile raFile = new RandomAccessFile(new File(fileName), "rw");
				raFile.seek(start);
				byte[] buf = new byte[1024];
				int len = -1;
				//System.out.println(length + ":" + sum);
				synchronized (sum) {
					while ((len = in.read(buf)) != -1) {
						raFile.write(buf, 0, len);
						sum += len;
						System.out.println("�����---" + sum * 100 / length + "%");
					}
				}
				raFile.close();
			}
			conn.disconnect();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}