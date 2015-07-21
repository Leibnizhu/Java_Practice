/*MultiThreadDemo1---Leibniz.Hu 2015.07.12.
* Try multi thread.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/

class TestThread extends Thread {
	TestThread(String name) {
		super(name);
	}
	public void run() {
		for(int x=0; x<20; x++) {
			System.out.println("Thread: " + Thread.currentThread().getName() + "...x = " + x);
		}
		System.out.println("Thread: " + Thread.currentThread().getName() + "...OVER");
	}
}

class MultiThreadDemo1 {
	public static void main(String[] args) {
		TestThread testTh1 = new TestThread("Cudy");
		testTh1.start();
		TestThread testTh2 = new TestThread("佐藤さん");
		testTh2.start();
		for(int x=0; x<20; x++) {
			System.out.println("Thread: " + Thread.currentThread().getName() + "...x = " + x);
		}
		System.out.println("Thread: " + Thread.currentThread().getName() + "...OVER");
	}
}