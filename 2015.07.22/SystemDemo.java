/*SystemDemo---Leibniz.Hu 2015.07.21
* try System class methods.
@author Leibniz.Hu
@version 1.0
*/
import java.util.*;

class SystemDemo {
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		Properties ppTest = System.getProperties();
		for(String key : ppTest.stringPropertyNames()) {
			System.out.println(key + "---" + ppTest.getProperty(key));
		}
		System.out.println("It cost " + (System.currentTimeMillis() - t1) + " ms to create the information above.");
		System.out.println("e = " + Math.E);
		System.out.println("Pi = " + Math.PI);
	}
}