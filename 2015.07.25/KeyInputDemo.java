/*KeyInputDemo---Leibniz.Hu 2015.07.26
* Try System.in stream, read key input and show them on the screen,
* if input "over"(ignore upper/lower case), then quit the progress.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;

class KeyInputDemo {
	public static void main(String[] args) throws IOException {
		keyInput();
	}
	
	public static void keyInput() throws IOException {
		InputStream isKey = System.in;
		StringBuilder sbTemp = new StringBuilder();
		int ch = 0;
		
		while((ch = isKey.read()) != -1) {
			if(ch == '\r') {
				continue;
			} 
			if (ch == '\n') { //end of this input
				String temp = sbTemp.toString();
				if(temp.toLowerCase().equals("over")) { //ignore upper/lower case.
					break; //break this while, return to main(), and exit this progress.
				} else {
					System.out.println(temp.toUpperCase());
					sbTemp.delete(0, sbTemp.length()); //for the next input
				}
			} else {
				sbTemp.append((char)ch);
			}
		}
		
	}
}
