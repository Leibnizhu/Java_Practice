/* WebCrawlerDemo---Leibniz.Hu 2015.08.07
 * Try to achieve a web crawler. Recursion by all hyper-links in a web.
 * Find out all mail address in every web. 
 @ author Leibniz.Hu
 @ version 1.0
 */
import java.io.*;
import java.util.*;
import java.net.URL;

class WebCrawlerDemo {
	public static void main(String[] args) {
		//Web address of searching "联系邮箱" in bing.com
		URL url = new URL("http://cn.bing.com/search?q=%E8%81%94%E7%B3%BB%E9%82%AE%E7%AE%B1");
		//URL url = new URL(http://cn.bing.com/search?q=contact+email);  //Web address of searching "contact email" in bing.com
		
		webCrawler(url, 10);
	}
	
	/* @param URL url : parent url, read hyper-links in this url.
	 * @param int level : how many levels the recursions will go on.
	 */
	public static List<String> webCrawler(URL url, int level) {
		
	}
}
