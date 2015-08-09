/* WebCrawlerDemo---Leibniz.Hu 2015.08.07
 * Try to achieve a web crawler. Recursion by all hyper-links in a web.
 * Find out all mail address in every web. 
 @ author Leibniz.Hu
 @ version 1.0
 * History:
 * 2015.08.07 Created. Add main frame.
 * 2015.08.08 Coded webCrawler() function.
 * 2015.08.09 Add a HashSet to store scanned URLs, to avoid repetition.
 */
import java.io.*;
import java.util.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

class WebCrawlerDemo {
	//To store all scanned URLs.
	private static HashSet<String> hsWeb = new HashSet<String>();
	
	public static void main(String[] args) throws Exception {
		//Web address of searching "联系邮箱" in bing.com
		URL url = new URL("http://cn.bing.com/search?q=%E8%81%94%E7%B3%BB%E9%82%AE%E7%AE%B1");
		//URL url = new URL("http://cn.bing.com/search?q=contact+email");  //Web address of searching "contact email" in bing.com
		
		//File to store email addresses.
		BufferedWriter fwTest = new BufferedWriter(new FileWriter("email.txt"));
		
		//Start web crawler.
		webCrawler(url, fwTest, 10);
	}
	
	/* @param URL : parent url, read hyper-links in this url.
	 * @param BufferedWriter : a buffered file writer to store email addresss.
	 * @param int : how many levels the recursions will go on.
	 */
	public static void webCrawler(URL url, BufferedWriter bw, int level) throws Exception {
		System.out.println("Searching in level " + level + " web: " + url);
		BufferedReader brWeb = new BufferedReader(new InputStreamReader(url.openStream()));
		//Regax for email address.
		Pattern pEmail = Pattern.compile("\\w+@\\w+(\\.\\w+)+");
		//Regax for hyper links.
		Pattern pHyperlink = Pattern.compile("<\\s*a\\s*href\\s*=['\"](http.+?)['\"]");
		
		String line = null;
		while((line = brWeb.readLine()) != null) {
			Matcher mHyperlink = pHyperlink.matcher(line);
			Matcher mEmail = pEmail.matcher(line);
			//while find a new hyper link, and recursion level is bigger then 1, go on search it.
			while(mHyperlink.find()) {
				if(level > 0){
					String strURL = mHyperlink.group(1);
					//Judge if this url is scanned already, or stored web address is too large, then ignore it.
					if(hsWeb.contains(strURL) || hsWeb.size() > 10000) {
						continue;
					}
					//Not scanned before,then store it into HashSet and scan it.
					hsWeb.add(strURL);
					URL sonWeb = new URL(strURL);
					try{
						webCrawler(sonWeb, bw, --level);
					}catch ( SocketException ce) {
						System.out.println(ce);
						continue;
					}
				}
			}
			//Check email addresses  and store them into file.
			while(mEmail.find()) {
				bw.write(mEmail.group());
				bw.newLine();
				bw.flush();
			}
		} 
	}
}
