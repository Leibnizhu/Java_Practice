/*UploadClientDemo---Leibniz.Hu 2015.08.03
* Use TCP Protocol to achieve a client upload a txt file to server
* Client and Server should open in two Command Window.
@author Leibniz.Hu
@version 1.0
*/
import java.net.*;
import java.io.*;

class UploadClientDemo {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Client is ready to upload JavaFiles.txt...");
		File fTemp = new File("D:\\GitHub\\Java_Practice\\2015.07.28\\JavaFiles.txt");
		//Create TCP Client Socket
		Socket sTemp = new Socket("127.0.0.1", 13141);
		//Get File reader input stream and socket output stream.
		BufferedReader brFile = new BufferedReader(new FileReader(fTemp));
		PrintWriter psTemp = new PrintWriter(sTemp.getOutputStream(), true);
		//Read lines from file and then write them into socket stream.
		String line = null;
		System.out.print("[");
		while((line = brFile.readLine()) != null) {
			psTemp.println(line);
			System.out.print("-");
		}
		System.out.println("]");
		sTemp.shutdownOutput();
		//Receive massage from Server.
		BufferedReader brSocket = new BufferedReader(new InputStreamReader(sTemp.getInputStream()));
		line = brSocket.readLine();
		System.out.println(line);
		//close resource.
		sTemp.close();
		brFile.close();
		//System.out.println("Upload successfully...");
	}
}
