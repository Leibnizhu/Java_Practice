/*CopyFileDemo---Leibniz.Hu 2015.07.21
* Use Reader and Writer to copy a file from D:\ to E:\ .
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;

class CopyFileDemo {
	public static void main(String[] args) {
		FileReader frTest = null;
		FileWriter fwTest = null;

		try {
			frTest = new FileReader("D:\\demo1.txt");
			fwTest = new FileWriter("E:\\demo1.txt");
			//Read character into buffer.
			char[] cBuffer = new char[1024];
			//Write character from buffer into file.
			int readLen = 0;
			while((readLen = frTest.read(cBuffer)) != -1) {
				fwTest.write(new String(cBuffer, 0, readLen));
			}
		} catch(IOException exp) {
			System.out.println(exp); //Print out error.
		}
		finally {
			//Anyway, if frTest/fwTest is not null, we should close the file
			//and handle the exception.
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
