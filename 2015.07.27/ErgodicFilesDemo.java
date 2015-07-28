/*ErgodicFilesDemo---Leibniz.Hu 2015.07.27
* Ergodic all files including files in sub-directories.
* Show them in a special tree-like format.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;
import java.util.*;

class ErgodicFilesDemo{
	public static void main(String[] args) {
		File dirTest = new File("D:\\GitHub\\Java_Practice");
		ergodicFiles(dirTest, 0);
	}
	
	public static void ergodicFiles(File dir, int level) {
		//Get file objects in current directory.
		File[] files = dir.listFiles();
		//Print formatted
		System.out.println(getTabString(level) + dir.getName());
		//Ergodic current directory.
		level++;
		for(int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) {
				ergodicFiles(files[i], level);
			} else {
				System.out.println(getTabString(level) + files[i].getName());
			}
		}
	}
	
	//Return space and dash to complete a tree-like format.
	public static String getTabString(int level) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < level; i++) {
			sb.append("    ");
		}
		sb.append("|---");
		return sb.toString();
	}
}