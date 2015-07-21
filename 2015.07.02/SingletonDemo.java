/*Just Try SingleTon Design Mode---Leibniz.Hu 2015.07.02*/

class SingletonDemo {
	public static void main (String[] args) {
		HungrySingleton	hsTest1 = HungrySingleton.getInstance();
		HungrySingleton hsTest2 = HungrySingleton.getInstance();
		System.out.println(hsTest1.getNumber());
		hsTest2.setNumber(10); //hsTest2 and hsTest1 point to the same instance
		System.out.println(hsTest1.getNumber());

		LazySingleton hsTest3 = LazySingleton.getInstance();
		LazySingleton hsTest4 = LazySingleton.getInstance();
		System.out.println(hsTest4.getNumber());
		hsTest3.setNumber(7); //hsTest3 and hsTest4 point to the same instance
		System.out.println(hsTest4.getNumber());
	}
}

//Hungry Singleton Design Mode, create the instance at first when the class be loaded.
class HungrySingleton {
	private int number = 3;
	private static HungrySingleton pHungry = new HungrySingleton(); //create instance right now.
	private HungrySingleton() {} //private constructor, the instance could not be created outside this class.

	public static HungrySingleton getInstance() {
		return pHungry;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}

//Lazy Singleton Design Mode, create a null object when class be loaded;
//create the instance only when call getInstance().
class LazySingleton {
	private int number = 5;
	private static LazySingleton pLazy = null;
	private LazySingleton() {}

	public static LazySingleton getInstance () {
		//if no instance exists, then create it first.
		if (pLazy==null) {
			pLazy = new LazySingleton();
		}
		return pLazy;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}