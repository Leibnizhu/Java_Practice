import java.net.*;
import java.io.*;

class UDPSender implements Runnable {
	//COnstructor by DatagramSocket
	private DatagramSocket dsSender;
	public UDPSender(DatagramSocket ds) {
		dsSender = ds;
	}
	
	//Override run()
	public void run() {
		try{
			//new a BufferedReader to store key-in massage.
			BufferedReader brTemp = new BufferedReader(new InputStreamReader(System.in));
			String msg = null;
			
			//in this loop read key-in massage and package it into a DatagramPacket
			//And then send it.
			while((msg = brTemp.readLine()) != null) {
				byte[] buf = msg.getBytes();
				DatagramPacket dpSend = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.0.255"), 13145);
				dsSender.send(dpSend);
				
				if(msg.equals("886") || msg.equals("over")) {
					break;
				}
			}
		} catch (Exception exp) {
			System.out.println(exp);
		} finally {
			dsSender.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		DatagramSocket dsSender = new DatagramSocket();
		//DatagramSocket dsReceiver = new DatagramSocket(13145);
		
		new Thread(new UDPSender(dsSender)).start();
		//new Thread(new UDPReceiver(dsReceiver)).start();
	}
}
