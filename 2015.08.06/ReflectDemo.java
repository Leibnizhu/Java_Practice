/*ReflectDemo---Leibniz.Hu 2015.08.03
* Try to use reflect to simulate a computer with usb interface.
* read USB devices from a properties file.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;
import java.util.*;

class ReflectDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		//Create a computer.
		computer pcTest = new computer();
		pcTest.run();
		
		//Read USB devices from a properties file.
		Properties prop = new Properties();
		File confFile = new File("USB.conf");
		FileInputStream fisTemp = new FileInputStream(confFile);
		prop.load(fisTemp);
		
		//Ergodic USb devices and use them.
		for(int i = 0; i < prop.size(); i++) {
			String usbDeviceName = prop.getProperty("USB" + (i+1));
			Class usbClass = Class.forName(usbDeviceName);
			USB usbDevice = (USB)usbClass.newInstance();
			pcTest.useUSB(usbDevice);
		}
	}
}

class computer {
	public void run() {
		System.out.println("Computer is running...\n");
	}
	
	public void useUSB(USB usbDevice) {
		usbDevice.insert();
		usbDevice.unplug();
		System.out.println();
	}
}

//USB interface
interface USB {
	public void insert();
	public void unplug();
}

//Three USB devices implements USB interface
class uDisk implements USB {
	public void insert() {
		System.out.println("USB Disk inserted...");
	}
	
	public void unplug() {
		System.out.println("USB Disk unplugged...");
	}
}

class uNetcard implements USB {
	public void insert() {
		System.out.println("USB Netcard inserted...");
	}
	
	public void unplug() {
		System.out.println("USB Netcard unplugged...");
	}
}

class uLED implements USB {
	public void insert() {
		System.out.println("USB LED inserted...");
	}
	
	public void unplug() {
		System.out.println("USB LED unplugged...");
	}
}