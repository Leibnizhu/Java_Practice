/*Vampire Number---Leibniz.Hu 2015.07.04
from <<Thinking in Java>> Page 75 Execise 9
@author Leibniz.Hu
@version 1.0.0
*/

public class VampireNumber {
	public static void main(String[] args) {
		getAllVampireNum();
	}

	static void getAllVampireNum() {
		for (int num=1001; num<9999; num++) {
			//skip numbers end with 00.
			/*if (num % 100 == 0) {
				continue;
			}*/

			int temp = num;
			int[] digit = new int[4];
			//separate num to 4 digits, digit[0] is lowest digit.
			for (int i=0; i<4; i++) {
				digit[i] = temp % 10;
				temp /= 10;
				//System.out.println(digit[i]); //for debug only
			}
			/*judge if num is a vampire number.
			* for a vampire-number, lowest digit of two multiplier multiplies equal to the lowest digit of vampire-number.
			* e.g. 1260=21*60, then 1*0==0; 1827=87*21, then 7*1==7.
			* so we judge if num has a digit combination which can  meet this condition.\
			* readability is worse, but more efficient.
			*/
			if ( (digit[0]*digit[1])%10 == digit[0] && judgeVampire(num, digit, 2, 3, 0, 1)) {
				continue;
			} else if ( (digit[0]*digit[2])%10 == digit[0] && judgeVampire(num, digit, 1, 3, 0, 2)) {
				continue;
			} else if ( (digit[0]*digit[3])%10 == digit[0] && judgeVampire(num, digit, 1, 2, 0, 3)) {
				continue;
			} else if ( (digit[1]*digit[2])%10 == digit[0] && judgeVampire(num, digit, 0, 3, 1, 2)) {
				continue;
			} else if ( (digit[1]*digit[3])%10 == digit[0] && judgeVampire(num, digit, 0, 2, 1, 3)) {
				continue;
			} else if ( (digit[2]*digit[3])%10 == digit[0] && judgeVampire(num, digit, 0, 1, 2, 3)) {
				continue;
			}
		}
	}

	/*for numbers meet the lowest digit condition, do the next judge, try two possibilities;
	* if meet the vampire-number condition, the show the result.
	*/
	static boolean judgeVampire(int num, int[] digit, int bit0, int bit1, int bit2, int bit3) {
		int numVam1 = digit[bit0] * 10 + digit[bit2];
		int numVam2 = digit[bit1] * 10 + digit[bit3];
		if (numVam1 * numVam2 == num) {
			printVampaire(num, numVam1, numVam2);
			return true;
		}
		numVam1 = digit[bit1] * 10 + digit[bit2];
		numVam2 = digit[bit0] * 10 + digit[bit3];
		if (numVam1 * numVam2 == num) {
			printVampaire(num, numVam1, numVam2);
			return true;
		} else {
			return false;
		}
	}

	//print out the result in a multiplier format.
	static void printVampaire(int num, int num0, int num1) {
		System.out.println(num + " = " + num0 + " * " + num1);
	}
}
