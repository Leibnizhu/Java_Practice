/*copyBinaryFileDemo---Leibniz.Hu 2015.07.25
* Copy a binary file use BufferedInputStream/BufferedOutputStream class.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;

class copyBinaryFileDemo {
	public static void main(String[] args) throws IOException {
		String source = "E:\\DSCN3859_meitu_1.jpg";
		String destination = "E:\\Demo1.jpg";
		copyBinFile(source, destination);
	}
	
	public static void copyBinFile(String source, String destination) throws IOException {
		BufferedInputStream bisTest = new BufferedInputStream(new FileInputStream(source));
		BufferedOutputStream bosTest = new BufferedOutputStream(new FileOutputStream(destination));
		
		byte[] bBuff = new byte[1024];
		int lenRead = 0;
		while((lenRead = bisTest.read(bBuff)) != -1) {
			bosTest.write(bBuff, 0, lenRead);
		}
	}
}
