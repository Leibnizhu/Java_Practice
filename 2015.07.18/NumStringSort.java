/*NumStringSort---Leibniz.Hu 2015.07.18
* Sort numbers in a string.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0
*/

import java.util.Arrays;

class NumStringSort {
	static final String SPACE = " ";
	
	public static void main(String[] args) {
		String strTest = "20 78 9 -7 88 36 29";
		
		System.out.println(strTest);
		System.out.println(NumStrSort(strTest));
		//System.out.println(strTest);
	}
	
	public static String NumStrSort(String numStr) {
		//Transform numbers String into Strings array.
		String[] numStrs = numStr.split(SPACE);
		
		//Transform Strings into numbers.
		int[] numArray = new int[numStrs.length];
		for(int i = 0; i < numStrs.length; i++) {
			numArray[i] = Integer.parseInt(numStrs[i]);
		}
		
		//Sort numbers by bubble-sort.
		//Need import java.util.Arrays.
		Arrays.sort(numArray);
		
		//return a sorted numbers String
		StringBuffer sbTemp = new StringBuffer();
		for(int i = 0; i < numStrs.length; i++) {
			if(i != numStrs.length - 1) {
				sbTemp.append(numArray[i] + SPACE); //Separates numbers by spaces.
			} else {
				sbTemp.append(numArray[i]); //The last number do not need follow with a space.
			}
		}
		return sbTemp.toString();
	}
}
