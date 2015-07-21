/*StringDemo3---Leibniz.Hu 2015.07.16.
* Exercises for String 3;
* Find out the max-length sub-string of two strings.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/

class StringDemo3 {
	public static void main(String[] args) {
		String strTest1 = "9wokeafuckksjdnkjan98";
		String strTest2 = "sagavfuckrewva";

		System.out.println("The longest sub-string is: " + getLongestSubstring(strTest1, strTest2));
	}

	public static String getLongestSubstring(String str1, String str2) {
		//Ensure which one is longer.
		String longer = null;
		String shorter = null;
		if(str1.length() > str2.length()) {
			longer = str1;
			shorter = str2;
		} else {
			longer = str2;
			shorter = str1;
		}

		for(int len = shorter.length(); len>0; len--) {
			for(int left = 0, right = len; right < shorter.length()+1; left++, right++) {
				String temp = shorter.substring(left, right);
				//System.out.println(temp);
				if(longer.contains(temp)) {
					return temp;
				}
			}
		}
		return null;
	}
}