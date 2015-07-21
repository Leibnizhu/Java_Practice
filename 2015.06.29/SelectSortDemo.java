/*Selection-Sort---2015.06.29 by Leibniz.Hu*/

public class SelectSortDemo {
	public static void main(String[] args) {
		int[] arr1= {3,124,5,34,87,35,99,12};
		Switch(arr1,0,1);
		PrintArr(arr1);
		SelectSort(arr1);
		PrintArr(arr1);
	}

	//Use selection-sort to sort the given array
	public static void SelectSort(int[] arr) {
		for(int OutCnt=0; OutCnt<arr.length-1; OutCnt++) {	//OutCnt:Outside Loop Counter
			int MinIndex=OutCnt;
			int MinValue=arr[OutCnt];
			for(int InCnt=OutCnt+1; InCnt<arr.length; InCnt++) {	//InCnt:Inside Loop Counter
				if(arr[InCnt]<MinValue)	{
					MinIndex=InCnt;
					MinValue=arr[InCnt];
				}
			}
			//System.out.println(arr[MinIndex]);
			Switch(arr, MinIndex, OutCnt);
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
		for(int Index=0; Index<arr.length-1; Index++) {
			System.out.print(arr[Index]+", ");
			System.out.println(arr[arr.length-1]+" ]");
		}
	}
}