/*ForEachDemo---Leibniz.Hu 2015.07.22
* Use For-each to traverse a list and a map.
@author Leibniz.Hu
@version 1.0
*/
import static java.lang.System.*; //Static import, to short down System.out.println()...
import java.util.*;

class ForEachDemo {
	public static void main(String[] args) {
		List<String> lsTest = new ArrayList<String>();
		lsTest.add("kwenf");
		lsTest.add("ujf");
		lsTest.add("sdggf");
		lsTest.add("cthsf");
		lsTest.add("dgjrf");

		out.println("Traverse the ArrayList");
		for(String str: lsTest) {
			out.println(str); //Thanks static import.
		}

		Map<Integer, String> mpTest = new HashMap<Integer, String>();
		mpTest.put(56,"vnensjd");
		mpTest.put(89,"mifg");
		mpTest.put(236,"wsdfgd");
		mpTest.put(154,"zjurnf");
		mpTest.put(67,"votnus");
		//Use keySet().
		out.println("\nUse keySet() to traverse the map");
		for(Integer key : mpTest.keySet()) {
			String value = mpTest.get(key);
			out.println(key + "---" + value);
		}

		//Use entrySet().
		out.println("\nUse entrySet() to traverse the map");
		for(Map.Entry<Integer,String> me : mpTest.entrySet()) {
			out.println(me.getKey() + "---" + me.getValue());
		}
	}
}
