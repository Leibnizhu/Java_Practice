/*CopyFileDemo---Leibniz.Hu 2015.07.21
* Use Reader and Writer to copy a file from C:\ to D:\ .
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;

class CopyFileDemo {
	public static void main(String[] args) {
		FileReader frTest = null;
		FileWriter fwTest = null;

		try {
			frTest = new FileReader("C:\\demo1.txt");
			fwTest = new FileWriter("D:\\demo1.txt");
			char[] cBuffer = new char[10];
			int readLen = 0;
			while((readLen = frTest.read(cBuffer)) != -1) {
				fwTest.write(cBuffer.toString());
			}
		} catch(IOException exp) {
			System.out.println(exp);
		}
		finally {
			if(frTest!=null) {
				try {
					frTest.close();
				} catch(IOException exp) {
					System.out.println(exp);
				}
			}
			if(fwTest!=null) {
				try {
					fwTest.close();
				} catch(IOException exp) {
					System.out.println(exp);
				}
			}
		}
	}
}
