/*LargestDiskDemo---Leibniz.Hu 2015.07.27
* Show the largest free space disk.
@author Leibniz.Hu
@version 1.0
*/
import java.io.*;
import java.util.*;

class LargestDiskDemo {
	public static void main(String[] args) {
		//Get disks.
		File[] fTemp = File.listRoots();
		TreeMap<Long, String> disks = new TreeMap<Long, String>();
		for(File disk: fTemp) {
			disks.put(disk.getFreeSpace(), disk.toString());
		}
		//Get largest free space key.
		Map.Entry<Long, String> me = disks.lastEntry();
		//Show informations.
		System.out.println(me.getValue() + ".\nFree Space : " + me.getKey()/1024/1024/1024 + "G");
	}
}