/*FilterListDemo---Leibniz.Hu 2015.07.27
* List files with file filter.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;
import java.util.*;

class FilterListDemo {
	public static void main(String[] args) {
		File dir = new File("E:\\Download");
		//List files filtered by file extension(.zip)
		String[] nameFiltered  = dir.list(new ExtensionFilter("zip"));
		System.out.println("Zip Files including:\n");
		for(String str: nameFiltered) {
			System.out.println(str);
		}

		//List all sub-directories.
		File[] subDirectory = dir.listFiles(new DirectoryFilter());
		System.out.println("\nSub-Directories including:\n");
		for(File file: subDirectory) {
			System.out.println(file.getName());
		}
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

class DirectoryFilter implements FileFilter {
	public boolean accept(File pathname) {
		return pathname.isDirectory();
	}
}