/* WebCrawlerDemo2---Leibniz.Hu 2015.08.09
 * Try to achieve a web crawler. Recursion by all hyper-links in a web.
 * Find out all mail address in every web.
 @ author Leibniz.Hu
 @ version 1.1
 * History:
 * 2015.08.07 Created. Add main frame.
 * 2015.08.08 Coded webCrawler() function.
 * 2015.08.09 Add a HashSet to store scanned URLs and Emails, to avoid repetition.
 *            Create multi-thread version, and fixed some bugs.
 */
import java.io.*;
import java.util.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

class WebCrawlerDemo2 {
	//To store all scanned URLs.
	private static HashSet<String> hsWeb = new HashSet<String>();
	private static HashSet<String> hsEmail = new HashSet<String>();

	public static void main(String[] args) throws Exception {
		//Web address of searching "联系邮箱" in bing.com
		URL url = new URL("http://cn.bing.com/search?q=%E8%81%94%E7%B3%BB%E9%82%AE%E7%AE%B1");
		//url = new URL("http://cn.bing.com/search?q=contact+email");  //Web address of searching "contact email" in bing.com
		url = new URL("http://tieba.baidu.com/f?ie=utf-8&kw=%E9%82%AE%E7%AE%B1&fr=search");

		//File to store email addresses.
		BufferedWriter fwTest = new BufferedWriter(new FileWriter("email.txt"));
		//Clear error log file.
		new File("error.log").delete();

		//Start web crawler.
		new Thread(new webCrawler(url, fwTest, 10, hsWeb, hsEmail)).start();

		//fwTest.close();
		System.out.println("main() finished.");
	}
}

class webCrawler implements Runnable {
	/* @param URL : parent url, read hyper-links in this url.
	 * @param BufferedWriter : a buffered file writer to store email addresss.
	 * @param int : how many levels the recursions will go on.
	 */
	URL url;
	BufferedWriter bw;
	int level;
	HashSet<String> hsWeb;
	HashSet<String> hsEmail;

	webCrawler(URL url, BufferedWriter bw, int level, HashSet<String> hsWeb, HashSet<String> hsEmail) {
		this.url = url;
		this.bw = bw;
		this.level = level;
		this.hsWeb = hsWeb;
		this.hsEmail = hsEmail;
	}

	public void run() {
		System.out.println("Start. Level " + level + " : " + url);
		try {
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
					if(level > 0) {
						String strURL = mHyperlink.group(1);
						//Judge if this url is scanned already, or stored web address is too large, then ignore it.
						if(hsWeb.contains(strURL) || hsWeb.size() > 10000) {
							break;
						}
						//Not scanned before,then store it into HashSet and scan it.
						hsWeb.add(strURL);
						URL sonWeb = new URL(strURL);

						//webCrawler(sonWeb, bw, --level);
						new Thread(new webCrawler(sonWeb, bw, level-1, hsWeb, hsEmail)).start();
						//System.out.println("Found. Level " + level + " : " + strURL);
						/* }catch ( Exception e) {
							BufferedWriter temp = new BufferedWriter(new FileWriter("error.log",true));
							temp.write(e.toString());
							temp.flush();
							temp.close();
							//System.out.println(ce);
							continue;
						} */
					}
				}
				//System.out.println("Hyper Link Finished. Level " + level + " web: " + url);
				//Check email addresses  and store them into file.
				while(mEmail.find()) {
					String strEmail = mEmail.group();
					if(hsEmail.contains(strEmail)) {
						break;
					} else {
						hsEmail.add(strEmail);
						//System.out.println(mEmail.group());
						bw.write(strEmail);
						bw.newLine();
						bw.flush();
						System.out.println(hsEmail.size() + " Email addresses found.");
					}
				}
			}
			/* if(level > 0) {
				System.out.println("Finished. Level " + level);
			} */
		} catch (Exception e) {
			System.out.println("Level "level + " : " + e);
		}
	}
}
