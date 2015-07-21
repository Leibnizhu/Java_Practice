/*StringDemo4---Leibniz.Hu 2015.07.16.
* Exercises for String 4;
* CLear the spaces before and after the string, like trim().
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/

class StringDemo4 {
	public static void main(String[] args) {
		String strTest = "      fuck fuckf u   ckfuc      kfuck     ";
		System.out.println(strTest);
		System.out.println(myTrim(strTest));
		System.out.println(myTrim("                    "));
	}

	public static String myTrim(String str) {
		int left = 0;
		int right = str.length() - 1;
		while(str.charAt(left) == ' ' && left < right) {
			//System.out.println(left + " " + right);
			left++;
		}
		//System.out.println(left);
		while(str.charAt(right) == ' ' && right > left) {
			right--;
		}
		return str.substring(left, ++right);
	}
}