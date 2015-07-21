/*LetterCount---Leibniz.Hu 2015.07.21
* Use TreeMap to count how many time each letter appears in a string.
* return a string like "a(1)b(3)......"
@author Leibniz.Hu
@version 1.0
*/
import java.util.*;

class LetterCount {
	public static void main(String[] args) {
		String strTest = "jaskdnfajwenwncaksjdweghxpcosond";
		System.out.println(letterCount(strTest));
	}
	
	public static String letterCount(String str) {
		//1. separate into char array.
		char[] letters = str.toCharArray();
		
		//2. new a TreeMap, traverse it, check if the letter in charArray is already in the map,
		//   if exists, new a key-value; else, get the value and add 1, then put it back.
		Map<Character, Integer> tmLetter = new TreeMap<Character, Integer>();
		for(int i = 0; i < letters.length; i++) {
			//Judge if not letter
			if(!(letters[i] >= 'a' && letters[i] <= 'z' || letters[i] >= 'A' && letters[i] <= 'Z' )) {
				continue;
			}
			
			//get value.
			Integer value = tmLetter.get(letters[i]);
			//change value.
			int temp = 1;
			if(value != null) {
				temp += value; //if exists, add 1.
			}
			tmLetter.put(letters[i], temp);
		}
		
		//3. return a string like "a(1)b(3)......"
		StringBuffer sbTemp = new StringBuffer();
		for(Iterator<Character> it = tmLetter.keySet().iterator(); it.hasNext(); ) {
			Character key = it.next();
			sbTemp.append(key + "(" + tmLetter.get(key) + ") ");
		}
		return sbTemp.toString();
	}
}
