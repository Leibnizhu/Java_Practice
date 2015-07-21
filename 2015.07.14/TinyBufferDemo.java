/*TinyBufferDemo---Leibniz.Hu 2015.07.14.
* Use monitor of Object and synchronized function,
* To simulate a small buffer reading  and writing.
* One thread write a new information, and then wakeup all thread and wait;
* Another thread read the information and print it out, and then wakeup all thread and wait.
* Try thread-thread communication.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/

class TinyBufferDemo {
	public static void main(String[] args) {
		PeopleBuffer pBuf = new PeopleBuffer();
		PeopleInput pIn = new PeopleInput(pBuf);
		PeopleOutput pOut = new PeopleOutput(pBuf);

		Thread th1 = new Thread(pIn);
		Thread th2 = new Thread(pOut);
		th1.start();
		th2.start();
	}
}

//class of this buffer, provided methods of setting and outputing.
class PeopleBuffer {
	private String name;
	private String sex;
	boolean readAble = false;

	public synchronized void infoSet(String name, String sex) {
		//Judge if write-able, if not, wait for notify.
		while(readAble) {
			try {
				this.wait();
			} catch(InterruptedException exp) {

			}
		}
		this.name = name;
		this.sex = sex;
		readAble = true;
		notifyAll(); //wake up all threads in thread-pool, to ensure infoOut can be run.
	}

	public synchronized void infoOut() {
		//Judge if read-able, if not, wait for notify.
		while(!readAble) {
			try {
				this.wait();
			} catch(InterruptedException exp) {

			}
		}
		System.out.println(Thread.currentThread().getName() + "......"+ name + "......" + sex);
		readAble = false;
		notifyAll();//wake up all threads in thread-pool, to ensure infoSet can be run.
	}
}

//Implement Runnable interface to input people information.
class PeopleInput implements Runnable {
	PeopleBuffer pb;

	PeopleInput(PeopleBuffer pb) {
		this.pb = pb;
	}

	public void run() {
		int flag = 0;
		int cnt = 0;
		while(cnt < 100) {
			if(flag == 1) {
				pb.infoSet("Cudy","Male");
			} else {
				pb.infoSet("Mayday","Femaleeeeeeee");
			}
			flag = (flag == 1)?0:1;
			cnt++;
		}
	}
}

//Implement Runnable interface to output/print people information.
class PeopleOutput implements Runnable {
	PeopleBuffer pb;

	PeopleOutput(PeopleBuffer pb) {
		this.pb = pb;
	}

	public void run() {
		int cnt = 0;
		while(cnt < 100) {
			pb.infoOut();
			cnt++;
		}
	}
}
