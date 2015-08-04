import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

class UDPReceiver implements Runnable {
	//COnstructor by DatagramSocket
	private DatagramSocket dsReceiver;
	public UDPReceiver(DatagramSocket ds) {
		dsReceiver = ds;
	}
	
	//Override run()
	public void run() {
		try{
			while(true) {
				byte[] buf = new byte[1024];
				DatagramPacket dpReceive = new DatagramPacket(buf, buf.length);
				dsReceiver.receive(dpReceive);
				
				String ip = dpReceive.getAddress().getHostAddress();
				String name = dpReceive.getAddress().getHostName();
				int port = dpReceive.getPort();
				String msg = new String(dpReceive.getData(), 0, dpReceive.getLength());
				String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
				
				System.out.println(ip + ":" + port + "(" + name + ")" + time + ":: " + msg);
				
				if(msg.equals("886") || msg.equals("over")) {
					break;
				}
			}
		} catch (Exception exp) {
			System.out.println(exp);
		} finally {
			dsReceiver.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		//DatagramSocket dsSender = new DatagramSocket();
		DatagramSocket dsReceiver = new DatagramSocket(13145);
		
		//new Thread(new UDPSender(dsSender)).start();
		new Thread(new UDPReceiver(dsReceiver)).start();
	}
}
