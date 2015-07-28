/*TrialVersionDemo---Leibniz.Hu 2015.07.28
* Ergodic all files including files in sub-directories.
* Show them in a special tree-like format.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;
import java.util.*;

class TrialVersionDemo {
	public static void main(String[] args) throws IOException {
		int times = checkExpired();
		if(times == -1) {
			throw new RuntimeException("\nTrial Expired, please pay for this app.");
		} else {
			System.out.println("Welcome to trial version app. This is your " + times + " times using this app.");
		}
	}
	
	public static int checkExpired() throws IOException {
		File cntFile = new File("countUsed.conf");
		//Judge if file exists(First time use).
		if(!cntFile.exists()) {
			cntFile.createNewFile();
		}
		
		FileInputStream fisTemp = new FileInputStream(cntFile);
		Properties propTemp = new Properties();
		//Load Properties.
		propTemp.load(fisTemp);
		String strCount = propTemp.getProperty("counts");
		int count = 0;
		if(strCount != null) { //not first time use.
			count = Integer.parseInt(strCount);
			if(count >= 5) {
				fisTemp.close();
				return -1;
			}
		}
		//anyway, increase.
		count++;
		
		//Write the change into Properties and .conf file.
		propTemp.setProperty("counts", String.valueOf(count));
		FileOutputStream fosTemp = new FileOutputStream("countUsed.conf");
		propTemp.store(fosTemp, "");
		
		//Close resources.
		fisTemp.close();
		fosTemp.close();
		
		//return used times.
		return count;
	}
}