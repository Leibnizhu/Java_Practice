/*CoderNManager---Leibniz.Hu 2015.07.05
* define classes of coders and managers, instance them and let them work.
* This execise is from video cource
@author Leibniz.Hu
@version 1.0.0
*/

public class CoderNManager {
	public static void main (String[] args) {
		Coder coder1 = new Coder("Jucy", 3242, 52139L);
		Coder coder2 = new Coder("Leibniz", 6234, 48972L);
		Manager manager1 = new Manager("Jack", 2342, 9874L, 1234L);
		coder1.work();
		coder2.work();
		manager1.work();
	}
}

abstract class Employee {
	private String name;
	private int id;
	private long pay;

	Employee(String name, int id, long pay) {
		this.name = name;
		this.id = id;
		this.pay = pay;
	}

	public String getName() {
		return name;
	}

	abstract void work();
}

class Coder extends Employee {
	Coder(String name, int id, long pay) {
		super(name, id, pay);
	}

	void work() {
		System.out.println("Coder " + this.getName() + " is coding...");
	}
}

class Manager extends Employee {
	long bonus;
	Manager(String name, int id, long pay, long bonus) {
		super(name, id, pay);
		this.bonus = bonus;
	}

	void work() {
		System.out.println("Manager " + this.getName() + " is managing projects...");
	}
}
