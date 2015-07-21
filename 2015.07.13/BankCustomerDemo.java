/*BankCustomerDemo---Leibniz.Hu 2015.07.13.
* Simulate one bank with 3 customers, each customer store $100 for 5 times,
* Each time the bank show how much it owns.
* Try synchronized function.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/

class Bank {
	private int sumMoney;

	public synchronized void addMoney(int fund) { //operate sumMoney at twice, must take these two sentence into a synchronized function or code block.
		sumMoney += fund;
		System.out.println("The bank has " + sumMoney + " in total.");
	}
}

class Customer implements Runnable {
	private Bank cusBank = new Bank();
	public void run() {
		for(int i=0; i<5; i++) {
			cusBank.addMoney(100);
		}
	}
}

class BankCustomerDemo {
	public static void main(String[] args) {
		Customer cus = new Customer();

		Thread thCustomer1 = new Thread(cus);
		Thread thCustomer2 = new Thread(cus);
		Thread thCustomer3 = new Thread(cus);

		thCustomer1.start();
		thCustomer2.start();
		thCustomer3.start();
	}
}