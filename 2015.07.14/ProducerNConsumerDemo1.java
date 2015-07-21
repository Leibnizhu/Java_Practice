/*ProducerNConsumerDemo1---Leibniz.Hu 2015.07.14.
* Extends the TinyBufferDemo:
* Two Producer produce together, they can make 10 products at one time at most;
* When producing finished, two Consumer consume together.
* Use monitor of Object and synchronized function again.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/


class ProducerNConsumerDemo1 {
	public static void main(String[] args) {
		Product pTemp = new Product();
		Producer pPro = new Producer(pTemp);
		Consumer pCon = new Consumer(pTemp);

		Thread th0 = new Thread(pPro);
		Thread th1 = new Thread(pPro);
		Thread th2 = new Thread(pCon);
		Thread th3 = new Thread(pCon);
		th0.start();
		th1.start();
		th2.start();
		th3.start();
	}
}

//class of this buffer, provided methods of setting and outputing.
class Product {
	private String[] name = new String[10];
	private int cnt = 1;
	private int ptPro, ptCon;
	boolean consumeAble = false;

	public synchronized void produce(String name) {
		//Judge if write-able, if not, wait for notify.
		while(consumeAble) {
			try {
				this.wait();
			} catch(InterruptedException exp) {

			}
		}
		this.name[ptPro] = name + " No." + cnt;
		cnt++;
		System.out.println(Thread.currentThread().getName()+"...Producer..."+this.name[ptPro]);
		ptPro++;
		if(ptPro == 10) {
			consumeAble = true;
			ptPro = 0;
			notifyAll(); //wake up all threads in thread-pool, to ensure infoOut can be run.
		}

	}

	public synchronized void consume() {
		//Judge if read-able, if not, wait for notify.
		while(!consumeAble) {
			try {
				this.wait();
			} catch(InterruptedException exp) {

			}
		}
		System.out.println(Thread.currentThread().getName()+"...Consumer.............."+this.name[ptCon]);
		ptCon++;
		if(ptCon == 10) {
			consumeAble = false;
			ptCon = 0;
			notifyAll();//wake up all threads in thread-pool, to ensure infoSet can be run.
		}
	}
}

//Implement Runnable interface to input people information.
class Producer implements Runnable {
	Product p;

	Producer(Product p) {
		this.p = p;
	}

	public void run() {
		int cnt = 0;
		while(cnt < 50) {
			p.produce("五食堂鸡翅");
			cnt++;
		}
	}
}

//Implement Runnable interface to output/print people information.
class Consumer implements Runnable {
	Product p;

	Consumer(Product p) {
		this.p = p;
	}

	public void run() {
		int cnt = 0;
		while(cnt < 50) {
			p.consume();
			cnt++;
		}
	}
}
