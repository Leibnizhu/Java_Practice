/*SplitNMergeDemo---Leibniz.Hu 2015.07.29
* Ergodic all files including files in sub-directories.
* Lists all Java files in a given directory into a file.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;
import java.util.*;

class SplitNMergeDemo {
	private final static int BUFFER_SIZE = 1048576; //1024*1024
	public static void main(String[] args) throws IOException {
		File fSplit = new File("D:\\09.bmp");
		splitFiles(fSplit);
		File dirMerge = new File("D:\\GitHub\\Java_Practice\\2015.07.28\\splitFiles");
		if(mergeFiles(dirMerge)) {
			System.out.println("Merged files successfully!");
		}
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
		//read bytes to buffer and write into a new split file.
		while((lenRead = fisTemp.read(buf)) != -1) {
			cnt++;
			fosTemp = new FileOutputStream(new File(dir, file.getName() + ".part" + cnt));
			fosTemp.write(buf, 0, lenRead);
			fosTemp.close();
		}
		
		//write property file.
		prop.setProperty("count", cnt + "");
		prop.setProperty("FileName", file.getName());
		fosTemp = new FileOutputStream(new File(dir, file.getName() + ".conf"));
		prop.store(fosTemp,"");
		
		//close resources.
		fosTemp.close();
		fisTemp.close();
	}
	
	public static boolean mergeFiles(File dir) throws IOException {
		//check . conf file.
		File[] confFiles = dir.listFiles(new ExtensionFilter("conf"));
		if(confFiles.length != 1) {
			System.out.println("Could not find right configure file, please check it out.");
			return false;		
		}
		File confFile = confFiles[0];
		
		//get properties information.
		Properties prop = new Properties();
		FileInputStream fisTemp = new FileInputStream(confFile);
		prop.load(fisTemp);
		String filename = prop.getProperty("FileName");
		int cnt = Integer.parseInt(prop.getProperty("count"));
		
		//get split files array.
		File[] splitFiles = dir.listFiles(new PrefixFilter(filename + "."));
		//Judge if split files is integrated.
		if(splitFiles.length != cnt) {
			System.out.println("Split files is not integrated, please check it out.");
			return false;
		}
		
		//store files into a set.
		ArrayList<FileInputStream> alFiles = new ArrayList<FileInputStream>();
		for(int i = 0; i < cnt; i++) {
			alFiles.add(new FileInputStream(splitFiles[i]));
		}
		
		//take these split files into a sequence-stream.
		Enumeration<FileInputStream> en = Collections.enumeration(alFiles);
		SequenceInputStream sisTemp = new SequenceInputStream(en);
		FileOutputStream fosTemp = new FileOutputStream(new File(dir, filename));
		//create a small buf.
		byte[] buf = new byte[1024];
		int lenWrite = 0;
		while((lenWrite = sisTemp.read(buf)) != -1) {
			fosTemp.write(buf, 0, lenWrite);
			//fosTemp.flush();
		}
		
		//close resources.
		fosTemp.close();
		sisTemp.close();
		
		return true;
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

class PrefixFilter implements FilenameFilter {
	private String prefix;
	PrefixFilter(String prefix) {
		this.prefix = prefix;
	}

	public boolean accept(File dir, String name) {
		return name.startsWith(prefix) && !(name.endsWith(".conf"));
	}
}