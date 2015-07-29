/*FilterFilesDemo---Leibniz.Hu 2015.07.28
* Ergodic all files including files in sub-directories.
* Lists all java files in a given directory into a file.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;
import java.util.*;

class FilterFilesDemo {
	public static void main(String[] args) throws IOException {
		File dirTest = new File("D:\\GitHub\\Java_Practice");
		BufferedWriter bwTest = new BufferedWriter(new FileWriter("JavaFiles.txt"));
		ergodicFiles(dirTest, bwTest);
	}

	public static void ergodicFiles(File dir, BufferedWriter destFile) throws IOException {
		//Get file objects in current directory.
		File[] files = dir.listFiles();
		//Ergodic current directory.
		for(int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) {
				ergodicFiles(files[i], destFile);
			} else if(files[i].getName().endsWith(".java")) {
				destFile.write(files[i].getAbsolutePath());
				destFile.newLine();
				destFile.flush();
			}
		}
	}
}
