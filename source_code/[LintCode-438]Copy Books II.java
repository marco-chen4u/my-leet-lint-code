/***
* LintCode 438. Copy Books II
Given n books and each book has the same number of pages. 
There are k persons to copy these books and the i-th person needs times[i] minutes to copy a book.
Each person can copy any number of books and they start copying at the same time. 
What's the best strategy to assign books so that the job can be finished at the earliest time?

Return the shortest time.

Example
	Example 1:
		Input: n = 4, times = [3, 2, 4]
		Output: 4
		Explanation: 
			First person spends 3 minutes to copy 1 book.
			Second person spends 4 minutes to copy 2 books.
			Third person spends 4 minutes to copy 1 book.
	Example 2:
		Input: n = 4, times = [3, 2, 4, 5]
		Output: 4
		Explanation: Use the same strategy as example 1 and the forth person does nothing.
***/
public class Solution {
    /**
     * @param n: An integer
     * @param times: an array of integers
     * @return: an integer
     */
    public int copyBooksII(int n, int[] times) {
        // check corner cases
		if (n <= 0 || times == null || times.length == 0) {
			return 0;
		}
		
		int start = 0;
		int end = getMaxTimeConsuming(n, times);
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			
			if (getBookCount(times, mid) >= n) {
				end = mid;
			}
			else {
				start = mid;
			}
		}
		
		if (getBookCount(times, start) <= n) {
			return start;
		}
		else {
			return end;
		}
    }
	
	private int getMaxTimeConsuming(int bookCount, int[] times) {
		int max = time[0];
		
		for (int time : times) {
			max = Math.max(max, time);
		}
		
		return max * bookCount;
	}
	
	private int getBookCount(int[] times, int taskOfTotalTime) {
		int count = 0;
		
		for (int time : times) {
			count += taskOfTotalTime / time;
		}
		
		return count;
	}
}