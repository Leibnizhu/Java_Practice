/*DateDemo---Leibniz.Hu 2015.07.21
* try Date class methods.
@author Leibniz.Hu
@version 1.0
*/
import java.util.*;
import java.text.*;

class DateDemo {
	public static void main(String[] args) throws ParseException {
		demo1();
		demo2();
		demo3();
	}

	//Transform time milliseconds into date object.
	static void demo1() {
		long time1 = System.currentTimeMillis();
		Date date1 = new Date(time1);
		System.out.println(date1);
	}

	//Format date by different pattern, and custom style.
	static void demo2() {
		Date date1 = new Date();
		DateFormat dfTest = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT);
		String str = dfTest.format(date1);
		System.out.println(str);

		dfTest = new SimpleDateFormat("yyyy==MM==dd...");
		System.out.println(dfTest.format(date1));
	}

	//Calculate the gap between two dates.
	static void demo3() throws ParseException {
		String str1 = "2007=02=14";
		String str2 = "2015=07=22";
		//Parse into Date object.
		DateFormat dfTest = new SimpleDateFormat("yyyy=MM=dd");
		Date date1 = dfTest.parse(str1);
		Date date2 = dfTest.parse(str2);
		//Calculate day-gaps.
		long timeGap = Math.abs(date1.getTime() - date2.getTime());
		//Use a new Date format and print it.
		dfTest = DateFormat.getDateInstance(DateFormat.LONG);
		System.out.println("There are " + timeGap/1000/60/60/24 + " days between " + dfTest.format(date1) + " and " + dfTest.format(date2));

	}
}