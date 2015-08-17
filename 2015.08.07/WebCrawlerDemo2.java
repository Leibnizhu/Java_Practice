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
 * 2015.08.10 Fixed some bugs, add thread counts detecting and web address type detecting.
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
		URL url;
		if(args.length == 1) {
			url = new URL(args[0]);
		} else {
			url = new URL("http://cn.bing.com/search?q=%E8%81%94%E7%B3%BB%E9%82%AE%E7%AE%B1");//Searching "联系邮箱" in bing.com
			//url = new URL("http://www.baidu.com/s?wd=%E8%81%94%E7%B3%BB%E9%82%AE%E7%AE%B1");
			//url = new URL("http://cn.bing.com/search?q=contact+email");  //Searching "contact email" in bing.com
			//url = new URL("http://tieba.baidu.com/f?ie=utf-8&kw=%E9%82%AE%E7%AE%B1&fr=search");
		}

		//File to store email addresses.
		BufferedWriter fwTest = new BufferedWriter(new FileWriter("email.txt", true));
		//Clear error log file.
		new File("error.log").delete();

		//Read existed email addresses in txt file, and store to HashSet
		BufferedReader frTest = new BufferedReader(new FileReader("email.txt"));
		String line = null;
		while((line = frTest.readLine()) != null) {
			hsEmail.add(line);
		}
		frTest.close();

		//Start web crawler.
		new Thread(new webCrawler(url, fwTest, 10, hsWeb, hsEmail, hsEmail.size())).start();

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
	int originSize;

	webCrawler(URL url, BufferedWriter bw, int level, HashSet<String> hsWeb, HashSet<String> hsEmail, int originSize) {
		this.url = url;
		this.bw = bw;
		this.level = level;
		this.hsWeb = hsWeb;
		this.hsEmail = hsEmail;
		this.originSize = originSize;
	}

	public void run() {
		System.out.println("Start. Level " + level + " : " + url);
		try {
			BufferedReader brWeb = new BufferedReader(new InputStreamReader(url.openStream()));
			//Regax for email address.
			Pattern pEmail = Pattern.compile("\\w+@\\w+(\\.\\w{1,4})+");
			//Regax for hyper links.
			Pattern pHyperlink = Pattern.compile("<\\s*a\\s*href\\s*=['\"](http.+?)['\"]");

			String line = null;
			while((line = brWeb.readLine()) != null) {
				Matcher mHyperlink = pHyperlink.matcher(line);
				Matcher mEmail = pEmail.matcher(line);
				//when found a new hyper link, and recursion level is bigger then 1, go on search it.
				while(mHyperlink.find()) {
					if(level > 0) {
						String strURL = mHyperlink.group(1);
						//Judge if this url is scanned already, or stored web address is too large, then ignore it.
						if(hsWeb.contains(strURL)) {
							continue;
						}
						while(Thread.activeCount() >= 5000) {
							System.out.println("Thread stack full!");
							Thread.sleep(10000);
						}
						//Not scanned before,then store it into HashSet and scan it.
						hsWeb.add(strURL);
						URL sonWeb = new URL(strURL);
						
						if(!strURL.endsWith(".exe") && !strURL.endsWith(".rar") && !strURL.endsWith(".zip") && !strURL.endsWith(".jpg")) {
							//webCrawler(sonWeb, bw, --level);
							Thread th = new Thread(new webCrawler(sonWeb, bw, level-1, hsWeb, hsEmail, originSize));
							th.setPriority((level > 10)? 10: level);
							th.start();
						}
					}
				}
				//System.out.println("Hyper Link Finished. Level " + level + " web: " + url);
				//Check email addresses  and store them into file.
				while(mEmail.find()) {
					String strEmail = mEmail.group();
					if(hsEmail.contains(strEmail)) {
						continue;
					} else {
						hsEmail.add(strEmail);
						//System.out.println(mEmail.group());
						synchronized(this) {
							bw.write(strEmail);
							bw.newLine();
							bw.flush();
						}
						System.out.println((hsEmail.size() - originSize) + "/" + hsEmail.size() + " Emails found: " + strEmail);
					}
				}
				//System.out.println("Email line Finished. Level " + level + " web: " + url);
			}
			if(level > 0) {
				System.out.println("Finished. Level " + level + ". " + Thread.activeCount() + " Threads.");
			}
		} catch (Exception e) {
			try {
				BufferedWriter temp = new BufferedWriter(new FileWriter("error.log",true));
				temp.write("Level " + level + " " + url + " : " + e.toString());
				temp.newLine();
				temp.flush();
				temp.close();
			} catch(Exception e2) {
			}
			//System.out.println("Level " + level + " : " + e);
		}
	}
}
