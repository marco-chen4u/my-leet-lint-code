/***
* LintCode 234. Web Crawler
Implement a webpage Crawler to crawl webpages of http://www.wikipedia.org/. 
To simplify the question, let's use url instead of the the webpage content.

Your crawler should:
	1.Call HtmlHelper.parseUrls(url) to get all urls from a webpage of given url.
	2.Only crawl the webpage of wikipedia.
	3.Do not crawl the same webpage twice.
	4.Start from the homepage of wikipedia: http://www.wikipedia.org/
	
Example
	Example 1
		Input:
			"http://www.wikipedia.org/": ["http://www.wikipedia.org/help/"]
			"http://www.wikipedia.org/help/": []
		Output: ["http://www.wikipedia.org/", "http://www.wikipedia.org/help/"]

	Example 2
		Input:
			"http://www.wikipedia.org/": ["http://www.wikipedia.org/help/"]
			"http://www.wikipedia.org/help/": ["http://www.wikipedia.org/","http://www.wikipedia.org/about/"]
			"http://www.wikipedia.org/about/": ["http://www.google.com/"]
		Output: 
			["http://www.wikipedia.org/", "http://www.wikipedia.org/help/", "http://www.wikipedia.org/about/"]

Notice
	You need do it with multithreading.
	You can use up to 3 threads
***/
/**
 * public class HtmlHelper {
 *     public static List<String> parseUrls(String url);
 *         // Get all urls from a webpage of given url. 
 * }
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.net.URL;
 
public class Solution {
	// fields
	private final String DOMAIN_WIKIPEDIA = "wikipedia.org";
	private List<String> result = new ArrayList<String>();
	private Set<String> visited = new HashSet<String>();
	
	private ExecutorService threadPool = Executors.newFixedThreadPool(3);
	private int count = 0;
	private Lock lock = new ReentrantLock();
	
	private class CrawlingTask implements Runnable {
		// fields
		private String url;
		
		// constructor
		public CrawlingTask(String url) {
			this.url = url;
		}
		
		@Override
		public void run() {
			List<String> urlNeighbors = HtmlHelper.parseUrls(url);
			
			try {
				for (String next : urlNeighbors) {
					if (next.indexOf(DOMAIN_WIKIPEDIA) == -1 ||
						visited.contains(next)) {
						continue;
					}
					
					URL nextUrl = new URL(next);
					
					lock.lock();
					
					visited.add(next);					
					result.add(next);
					threadPool.execute(new CrawlingTask(next));
					count++;
					
					lock.unlock();
				}
			}
			catch(Exception ex) {
				// let it be
			}
			finally {
				count--;
			}
		}
	}
		
    /**
     * @param url: a url of root page
     * @return: all urls
     */
    public List<String> crawler(String url) {
		this.result.clear();
        if (url == null || url.length() == 0) {
			return result;
		}
		
		visited.add(url);
		result.add(url);
		threadPool.execute(new CrawlingTask(url));
		this.count++;
		
		try {
			while (this.count != 0) {
				Thread.sleep(5);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		threadPool.shutdown();
		
		return result;
    }
}