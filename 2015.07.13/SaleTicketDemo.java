/*SaleTicketDemo---Leibniz.Hu 2015.07.13.
* Simulate several windows to sale train tickets;
* Use Runnable interface to achieve multi thread with only one object;
* Use synchronized to ensure no operate the same ticket at one time.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/

class SaleTicketDemo{
	public static void main(String[] args){
		TicketWindow tw1 = new TicketWindow();
		TicketWindow tw2 = new TicketWindow(50);
		
		Thread thTicket1 = new Thread(tw1);
		Thread thTicket2 = new Thread(tw1);
		Thread thTicket3 = new Thread(tw2);
		Thread thTicket4 = new Thread(tw2);		
		
		thTicket1.start();
		thTicket2.start();
		thTicket3.start();
		thTicket4.start();		
	}
}

class TicketWindow implements Runnable{
	Object lock = new Object();
	private int sumTicket; //the whole number of tickets.
	
	TicketWindow(){
		sumTicket = 100; //default=100 tickets.
	}
	
	TicketWindow(int sum){
		sumTicket = sum; //can be define by user too.
	}
	
	public void run(){
		int cntSaled = 0;
		while(true){
			synchronized(lock){
				if(sumTicket > 0){
					/* use sleep()
					try{
						Thread.sleep(5);
					}
					catch(InterruptedException exp){
						
					}
					/**/
					System.out.println(Thread.currentThread().getName() + " is selling ticket No." + sumTicket--);
					cntSaled++;
				} else {
					break;
				}
			}
		}
		System.out.println("\n" + Thread.currentThread().getName() + " totally sold " + cntSaled); //At last, show how many tickets has been sold by each thread(window).
	}
}