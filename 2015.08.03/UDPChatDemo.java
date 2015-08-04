/*UDPChatDemo---Leibniz.Hu 2015.08.03
* Use UDP Protocol to make a LAN Chatting Tool
* Send massage to all IP in the LAN, anyone can receive it.
* If you try to test this demo in a PC, Please compile UDPReceiver.java and UDPSender.java and run
* If run in different PC, you can compile this file and run
@author Leibniz.Hu
@version 1.0
*/
import java.net.*;
import java.io.*;

class UDPChatDemo {
	public static void main(String[] args) throws IOException {
		DatagramSocket dsSender = new DatagramSocket();
		DatagramSocket dsReceiver = new DatagramSocket(13145);
		
		//new Thread(new UDPSender(dsSender)).start();
		new Thread(new UDPReceiver(dsReceiver)).start();
	}
}

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
}

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
}
