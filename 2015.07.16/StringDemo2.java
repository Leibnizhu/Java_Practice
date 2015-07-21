/*StringDemo2---Leibniz.Hu 2015.07.16.
* Exercises for String 2;
* Count how many times a sub string appear in a string.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0.0
*/

class StringDemo2 {
	public static void main(String[] args) {
		String strTest = "aaaaaaaaaafuckaaaaaaaafuckaaafuck";
		String strKey = "fuck";

		System.out.println("There are " + getSubstringCounts1(strTest, strKey) + " " +strKey + " in string " + strTest + " by method 1");
		System.out.println("There are " + getSubstringCounts2(strTest, strKey) + " " +strKey + " in string " + strTest + " by method 2");

	}

	//Search sub-string, then cut the source-string, and go on...
	public static int getSubstringCounts1(String strSource, String strKey) {
		int count = 0;
		int index = 0;
		while((index = strSource.indexOf(strKey, index)) != -1) {
			index += strKey.length();
			count++;
		}
		return count;
	}

	//Use split() and endsWith() methods to search sub-string...
	public static int getSubstringCounts2(String strSource, String strKey) {
		int result = strSource.split(strKey).length-1;//if starts with key string, the first result will be a empty string.
		result += strSource.endsWith(strKey)?1:0;
		return result;
	}
}