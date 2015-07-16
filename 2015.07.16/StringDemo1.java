/*StringDemo1---Leibniz.Hu 2015.07.16.
* Exercises for String 1;
* Bubble sort for String.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/

class StringDemo1 {
	public static void main(String[] args) {
		String[] arrTest = { "sdf", "hhd", "dfh5", "ds4gbe", "1gtg5c", "dsd" };
		printStringArray(arrTest);
		stringBubbleSort(arrTest);
		printStringArray(arrTest);
	}
	
	//Use bubble-sort to sort the given String array
	public static void stringBubbleSort(String[] strArr) {
		for (int outCnt = strArr.length-1; outCnt > 0; outCnt--) {	//outCnt: Outside Loop Counter
			for(int inCnt = 0; inCnt < outCnt; inCnt++) {	//inCnt: Inside Loop Counter
				if(strArr[inCnt].compareTo(strArr[inCnt+1]) > 0) {
					mySwitch(strArr, inCnt, inCnt+1);
				}
			}
		}
	}
		
	//exchanges by given indexes of the given String array.
	public static void mySwitch(String[] strArr, int index1, int index2) {
		String temp = strArr[index1];
		strArr[index1] = strArr[index2];
		strArr[index2] = temp;
	}
	
	//Print out the whole array, separates by comma
	public static void printStringArray(String[] strArr) {
		System.out.print("[ ");
		for (int index=0; index<strArr.length; index++) {
			if(index != strArr.length - 1) {
				System.out.print(strArr[index]+", ");
			} else {
				System.out.println(strArr[strArr.length-1]+" ]");
			}
		}
	}
}

