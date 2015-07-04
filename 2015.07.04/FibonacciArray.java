/*Fibonacci Array---Leibniz.Hu 2015.07.03
from <<Thinking in Java>> Page 75 Execise 8
@author Leibniz.Hu
@version 1.0
*/

public class FibonacciArray {
	public static void main(String[] args) {
		//check agruments from CLI
		if (args.length != 0) {
			System.out.println(getFibonacci(Integer.parseInt(args[0])));
		} else {
			System.out.println("Please input an integer after commands.");
		}
	}
	
	//Input an integer, return a Fibonacci array, which is ended with this integer;
	//If Fibonacci array cannot end by this integer, 
	//then the array ends by a smallest number larger then the argument.
	static String getFibonacci(int endNumber) {
		//check if an error input number.
		if (endNumber < 1) {
			return "Error Input. Please input an integer larger than 1.";
		}
		
		int fibNum1, fibNum2;
		fibNum1 = fibNum2 = 1;
		String result = fibNum1 + ", ";
		
		while(true) {
			if (fibNum2 < endNumber) {	//still not reach endNumber, keep iteration.
				result = result + fibNum2 + ", ";
				int temp = fibNum1 + fibNum2;
				fibNum1 = fibNum2;
				fibNum2 = temp;				
			} else if (fibNum2 > endNumber) {	//the final number is larger than endNumber, use brackets to take it in.
				result = result + "(" + fibNum2 + ")"; 
				return result;
			} else {	//reach endNumber, return the result string.
				result = result + fibNum2;
				return result;
			}
		}
	}
}
