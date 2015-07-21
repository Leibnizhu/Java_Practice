/*BinarySearch---2015.06.29 by Leibniz.Hu*/

public class BinarySearchDemo {
	public static void main(String[] args) {
		int[] arr1= {3,124,5,34,87,35,99,12};
		//Switch(arr1,0,1);
		PrintArr(arr1);
		BubbleSort(arr1);
		PrintArr(arr1);
		System.out.println("Index of 35 = "+BinarySearch(arr1,35));	//应为4
		System.out.println("Index of 40 = "+BinarySearch(arr1,40));	//应为-6，即5处插入
	}

	//Use Binary searching method to  searh the given  value in the given array.
	//If the value exists in the array, return the index;
	//Else, return the index if insert it into the array. return (-index-1).
	public static int BinarySearch(int[] arr, int SearchValue) {
		int MinIndex, MaxIndex, MidIndex;
		MinIndex=0;
		MaxIndex=arr.length-1;
		while (MinIndex<=MaxIndex) {
			MidIndex=(MinIndex+MaxIndex)>>1;
			if (arr[MidIndex]==SearchValue) {
				return MidIndex;
			} else if (arr[MidIndex]>SearchValue) {
				MaxIndex=MidIndex-1;
			} else {
				MinIndex=MidIndex+1;
			}
		}
		return -MinIndex-1;
	}

	//Use bubble-sort to sort the given array
	public static void BubbleSort(int[] arr) {
		for (int OutCnt=arr.length-1; OutCnt>0; OutCnt--) {	//OutCnt: Outside Loop Counter
			for(int InCnt=0; InCnt<OutCnt; InCnt++) {	//InCnt: Inside Loop Counter
				if(arr[InCnt]>arr[InCnt+1]) {
					Switch(arr, InCnt, InCnt+1);
				}
			}
		}
	}

	//exchanges by given indexs of the given array
	public static void Switch(int[] arr, int Index1, int Index2) {
		int temp=arr[Index1];
		arr[Index1]=arr[Index2];
		arr[Index2]=temp;
	}

	//Print out the whole array, seperates by comma
	public static void PrintArr(int[] arr) {
		System.out.print("[ ");
		for (int Index=0; Index<arr.length; Index++) {
			if(Index!=arr.length-1) {
				System.out.print(arr[Index]+", ");
			} else {
				System.out.println(arr[arr.length-1]+" ]");
			}
		}
	}
}