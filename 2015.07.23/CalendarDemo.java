/*CalendarDemo---Leibniz.Hu 2015.07.21
* Use TreeMap to count how many time each letter appears in a string.
* return a string like "a(1)b(3)......"
@author Leibniz.Hu
@version 1.0
*/
import java.util.*;

class CalendarDemo {
	public static void main(String[] args) {
		Calendar clTest = Calendar.getInstance();
		//Show get() method.
		System.out.println(getDateString(clTest));
		//Show set() method.
		clTest.set(clTest.get(Calendar.YEAR), 2, 14);
		System.out.println(getDateString(clTest));
		//Another way of set().
		clTest.set(Calendar.YEAR, 2007);
		System.out.println(getDateString(clTest));
		//Show add() method.
		clTest.add(Calendar.DAY_OF_MONTH, -12);
		System.out.println(getDateString(clTest));
	}
	
	static String getDateString(Calendar cl) {
		return cl.get(Calendar.YEAR) + "=" + cl.get(Calendar.MONTH) + "=" + cl.get(Calendar.DAY_OF_MONTH);
	}
}
