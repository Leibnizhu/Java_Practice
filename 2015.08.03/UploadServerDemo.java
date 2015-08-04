/*UploadServerDemo---Leibniz.Hu 2015.08.03
* Use TCP Protocol to achieve a client upload a txt file to server
* Client and Server should open in two Command Window.
@author Leibniz.Hu
@version 1.0
*/
import java.net.*;
import java.io.*;

class UploadServerDemo {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Server is ready to receive files...\nWaiting for client connect...");
		//Create TCP Server Socket
		ServerSocket ssTemp = new ServerSocket(13141);
		//Receive a Client object
		Socket sTemp = ssTemp.accept();
		System.out.println(sTemp.getInetAddress().getHostAddress() + " connected...");
		//Prepare input/output stream
		BufferedReader brFile = new BufferedReader(new InputStreamReader(sTemp.getInputStream()));
		BufferedWriter bwFile = new BufferedWriter(new FileWriter("Server.txt"));
		String line = null;
		//Begin to receive file.
		System.out.println("Server is receiving JavaFiles.txt...");
		while((line = brFile.readLine()) != null) {
			bwFile.write(line);
			bwFile.newLine();
			bwFile.flush();
		}
		//Send back massage to client to notice that received.
		PrintWriter pwTemp = new PrintWriter(sTemp.getOutputStream(), true);
		pwTemp.println("Server received successfully...");
		
		//close resource.
		ssTemp.close();
		sTemp.close();
		bwFile.close();
	}
}
