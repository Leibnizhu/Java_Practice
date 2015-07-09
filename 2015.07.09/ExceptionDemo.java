/*Try Custom Exception---Leibniz.HU 2015.07.09
@author Leibniz.Hu
@version 1.0.0
*/

class ExceptionDemo {
	public static void main(String[] args) throws MyNegativeIndexException {
		int[] array = new int[10];
		eDemo ed = new eDemo();
		//int num = ed.method(array,30);
		int num = ed.method(array,-30);
		//int num = ed.method(null,9);
		System.out.println("num = " + num);
		
	}
}

class eDemo {
	//MyNegativeIndexException extends from RuntimeException so that donot need to "throws" it
	public int method (int[] array, int index) /*throws MyNegativeIndexException*/ {
		if(array == null) {
			throw new NullPointerException();
		} else if(array.length <= index) {
			throw new ArrayIndexOutOfBoundsException("索引越界: " + index);
		} else if(index < 0) {
			throw new MyNegativeIndexException("索引小于0越界:" + index);
		}
		return array[index];
	}
}

//Custom Index<0 exception
class MyNegativeIndexException extends RuntimeException {
	MyNegativeIndexException(){
		;
	}
	
	MyNegativeIndexException(String msgError) {
		super(msgError);
	}
}