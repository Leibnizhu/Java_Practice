/* Interger transform to Hex/Binary/Octal---2015.06.29 by Leibniz.Hu*/

public class Int2Hex {
	public static void main(String[] args) {
		Int2Hex(5576);
		Int2Bin(5576);
		Int2Oct(5576);
	}
	//Transform to Hex
	public static void Int2Hex(int Value) {
		Trans2Any(Value, 15, 4);
	}
	
	//Transform to Binary
	public static void Int2Bin(int Value) {
		Trans2Any(Value, 1, 1);
	}
	
	//Transform to Octal
	public static void Int2Oct(int Value) {
		Trans2Any(Value, 7, 3);
	}
	//Transform to any bits form
	public static void Trans2Any(int Value, int Mask, int Offset) {
		if (Value==0) {
			System.out.println("0");
			return;
		}
		
		char[] SymTable={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] Result=new char[32];
		int Pos=Result.length;
		
		//Use Mask to get low Offset bits, and store it into array
		while(Value!=0) {
			int Temp=Value & Mask;
			Result[--Pos]=(SymTable[Temp]);
			Value=Value>>Offset;
		}
		
		//Print out the result array
		for (int Cnt=Pos;Cnt<Result.length;Cnt++) {
			System.out.print(Result[Cnt]);
		}
		System.out.println();
	}
}