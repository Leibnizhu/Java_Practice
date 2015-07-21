/*BubbleSort---2015.06.29 by Leibniz.Hu*/

public class BubbleSortDemo {
	public static void main(String[] args) {
		int[] arr1= {3,124,5,34,87,35,99,12};
		//Switch(arr1,0,1);
		PrintArr(arr1);
		BubbleSort(arr1);
		PrintArr(arr1);
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