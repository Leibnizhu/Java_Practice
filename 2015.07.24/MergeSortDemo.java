/*MergeSortDemo---Leibniz.Hu 2015.07.19
* Try to implement merge-sort.
@author Leibniz.Hu
@version 1.0
*/
import java.util.Arrays;

class MergeSortDemo{
	public static void main(String[] args) {
		int[] intArrTest = {3,124,5,34,87,35,99,12};
		System.out.println(Arrays.toString(intArrTest));
		mergeSort(intArrTest, 0, intArrTest.length - 1);
		System.out.println(Arrays.toString(intArrTest));
	}
	
	//Merge-Sort
	public static void mergeSort(int[] intArr, int indexLeft, int indexRight) {
		if(indexLeft < indexRight){
			int indexMid = (indexLeft + indexRight)/2;
			//System.out.println(indexMid);
			//1. Divide into two small sort and conquer respectively.
			mergeSort(intArr, indexLeft, indexMid);
			mergeSort(intArr, indexMid + 1, indexRight);
			//2. Merge two sort results.
			//2.1. Backup arrays.
			int[] arrLeft = Arrays.copyOfRange(intArr, indexLeft, indexMid + 1);
			//System.out.println(Arrays.toString(arrLeft));
			int[] arrRight = Arrays.copyOfRange(intArr, indexMid + 1, indexRight + 1);
			//System.out.println(Arrays.toString(arrRight));
			//2.2 compare two arrays
			int i = 0; //index for arrLeft.
			int j = 0; //index for arrRight.
			int k = indexLeft; //index for intArr.
			while(i < arrLeft.length && j < arrRight.length) {
				if(arrLeft[i] < arrRight[j]) {
					intArr[k++] = arrLeft[i++];
				} else {
					intArr[k++] = arrRight[j++];
				}
			}
			//System.out.println(Arrays.toString(intArr));
			//judge if numbers left in a sub array.
			if(i == arrLeft.length) {
				//System.out.println("1-" + i + "," + j + "," + k);
				//j--;
				for(; k <= indexRight; k++, j++) {
					intArr[k] = arrRight[j];
					//System.out.println(j + ":" + arrRight[j]);
				}
			}
			if(j == arrRight.length) {
				//System.out.println("2-" + i + "," + j + "," + k);
				//i--;	
				for(; k <= indexRight; k++, i++) {
					intArr[k] = arrLeft[i];
					//System.out.println(i + ":" + arrLeft[i]);
				}
			}
			//System.out.println(Arrays.toString(intArr));
		}
	}
}
