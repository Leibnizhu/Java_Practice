/*SplitNMergeDemo---Leibniz.Hu 2015.07.29
* Ergodic all files including files in sub-directories.
* Lists all java files in a given directory into a file.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;
import java.util.*;

class SplitNMergeDemo {
	private final static int BUFFER_SIZE = 1048576; //1024*1024
	public static void main(String[] args) throws IOException {
		File fTest = new File("D:\\09.bmp");
		splitFiles(fTest);
	}
	
	public static void splitFiles(File file) throws IOException {
		//define a buffer.
		byte[] buf = new byte[BUFFER_SIZE];
		//define streams.
		FileInputStream fisTemp = new FileInputStream(file);
		FileOutputStream fosTemp = null;
		Properties prop = new Properties();
		
		File dir = new File(".\\splitFiles");
		//if this directory not exists, create it(maybe include sub-directories).
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		int lenRead = 0;
		int cnt = 0;
		//read bytes to buffer and write into a new splited file.
		while((lenRead = fisTemp.read(buf)) != -1) {
			cnt++;
			fosTemp = new FileOutputStream(new File(dir, file.getName() + ".part" + cnt));
			fosTemp.write(buf, 0, lenRead);
			fosTemp.close();
		}
		
		//write propertiy file.
		prop.setProperty("count", cnt + "");
		//prop.setProperty("");
		fosTemp = new FileOutputStream(new File(dir, file.getName() + ".conf"));
		prop.store(fosTemp,"");
		
		//close resouces.
		fosTemp.close();
		fisTemp.close();
	}
}

class ExtensionFilter implements FilenameFilter {
	private String extension;
	ExtensionFilter(String extension) {
		this.extension = extension;
	}

	public boolean accept(File dir, String name) {
		return name.endsWith("." + extension);
	}
}