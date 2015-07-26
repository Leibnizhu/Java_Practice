/*copyStreamDemo---Leibniz.Hu 2015.07.26
* Copy a binary file use BufferedInputStream/BufferedOutputStream class.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;

class copyStreamDemo {
	public static void main(String[] args) throws IOException {
		copyStream1();
		copyStream2();
		copyStream3();
		copyStream4();
		copyStream5();
	}

	//1. Copy a text file.
	public static void copyStream1() throws IOException {
		BufferedReader brTemp = new BufferedReader(new FileReader("1.txt"));
		BufferedWriter bwTemp = new BufferedWriter(new FileWriter("1_copy.txt"));
		copyBuffered(brTemp, bwTemp);
		brTemp.close();
		bwTemp.close();
	}

	//2. Read keyboard and write to a text file.
	public static void copyStream2() throws IOException {
		BufferedReader brTemp = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bwTemp = new BufferedWriter(new FileWriter("2.txt"));
		copyBufferedOver(brTemp, bwTemp);
		bwTemp.close();
	}

	//3. Read a text file and show it on the console.
	public static void copyStream3() throws IOException {
		BufferedReader brTemp = new BufferedReader(new FileReader("1.txt"));
		BufferedWriter bwTemp = new BufferedWriter(new OutputStreamWriter(System.out));
		copyBuffered(brTemp, bwTemp);
		brTemp.close();
	}

	//4. Read keyboard input and show them on the console.
	public static void copyStream4() throws IOException {
		BufferedReader brTemp = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bwTemp = new BufferedWriter(new OutputStreamWriter(System.out));
		copyBufferedOver(brTemp, bwTemp);
	}

	//5. Copy a string into a text file by given encode table.
	public static void copyStream5() throws IOException {
		BufferedWriter bwTemp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("5.txt"), "GBK"));
		bwTemp.write("测试");
		bwTemp.flush();
		bwTemp.close();
	}

	public static void copyBuffered(BufferedReader brTemp, BufferedWriter bwTemp) throws IOException {
		String strTemp;
		while((strTemp = brTemp.readLine()) != null) {
			bwTemp.write(strTemp);
			bwTemp.newLine();
			bwTemp.flush();
		}
	}

	public static void copyBufferedOver(BufferedReader brTemp, BufferedWriter bwTemp) throws IOException {
		String strTemp;
		while((strTemp = brTemp.readLine()) != null) {
			if(strTemp.toLowerCase().equals("over")) {
				break;
			}
			bwTemp.write(strTemp.toUpperCase());
			bwTemp.newLine();
			bwTemp.flush();
		}
	}
}
