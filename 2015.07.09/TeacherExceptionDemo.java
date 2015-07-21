/*TeacherExceptionDemo---Leibniz.Hu 2015.07.09.
* Simulate teacher teachs and exception like bluescreen.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/
//import java.util.*;

class TeacherExceptionDemo {
	public static void main(String[] args) {
		Teacher tCang = new Teacher("蒼い空");
		try {
			tCang.lecture();
			tCang.setLaptopStatus(PcStatus.BLUE_SCREEN);
			tCang.lecture();
			tCang.setLaptopStatus(PcStatus.SMOKING);
			tCang.lecture();
			//Codes below will not be execute, because Line 17 will throw out exception to JVM to handle it
			tCang.setLaptopStatus(PcStatus.NORMAL);
			tCang.lecture();
		} catch(UnHandlableException expUnHandle) {
			System.out.println("We cannot handle this status");
		}
	}
}

class Teacher {
	private String name;
	public String getName() {
		return name;
	}

	private Computer laptop;
	public void setLaptopStatus(PcStatus status) {
		laptop.setStatus(status);
	}

	Teacher(String name) {
		this.name = name; //Get name.
		laptop = new Computer(); //Get computer.
	}

	private void test() {
		System.out.println("皆さん、練習をしてください！");
	}

	//Teacher teachs, let computer run first, then have the lecture.
	public void lecture()throws UnHandlableException {
		try{
			laptop.runPC();
			System.out.println("Teacher " + name + " is having lecture...");
			System.out.println("");
		} catch(BlueScreenException expBlueScreen) {
			System.out.println(expBlueScreen.toString()); //show information.
			laptop.resetPC(); //reset the computer.
			lecture(); //go on lecture.
		} catch(SmokingException expSmoking) {
			System.out.println(expSmoking.toString()); //show information.
			test();
			throw new UnHandlableException("Connot continue lecture because of " + expSmoking.getMessage());
		}
		finally{

		}
	}
}

//enum of PC status.
enum PcStatus {
	NORMAL, BLUE_SCREEN, SMOKING
}

class Computer {
	private PcStatus status = PcStatus.NORMAL;
	public void setStatus(PcStatus status) {
		this.status = status;
	}

	public void resetPC() {
		status = PcStatus.NORMAL;
		System.out.println("Computer is rebooting...");
	}

	//Computer runs, do something according the status.
	public void runPC() throws BlueScreenException, SmokingException {
		switch(status) {
		case NORMAL:
			System.out.println("Computer is normally running...");
			break;
		case BLUE_SCREEN:
			throw new BlueScreenException("The Laptop become blue-screen!!!");
		case SMOKING:
			throw new SmokingException("The laptop is inflame and smoking!!!");
		}
	}
}

//Define custom exception of computers
class BlueScreenException extends Exception {
	BlueScreenException(String msgExp) {
		super(msgExp); //Just let super class(Exception) to handle the massage.
	}
}

class SmokingException extends Exception {
	SmokingException(String msgExp) {
		super(msgExp); //Just let super class(Exception) to handle the massage.
	}
}

class UnHandlableException extends Exception {
	UnHandlableException(String msgExp) {
		super(msgExp); //Just let super class(Exception) to handle the massage.
	}
}