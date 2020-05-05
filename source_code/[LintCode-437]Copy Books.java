/***
* LintCode 437. Copy Books
Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.
These books list in a row and each person can claim a continous range of books. 

For example, one copier can copy the books from i-th to j-th continously, 
but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. 
What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Return the shortest time that the slowest copier spends.

Example
	Example 1:
		Input: pages = [3, 2, 4], k = 2
		Output: 5
		Explanation: 
			First person spends 5 minutes to copy book 1 and book 2.
			Second person spends 4 minutes to copy book 3.
	Example 2:
		Input: pages = [3, 2, 4], k = 3
		Output: 4
		Explanation: Each person copies one of the books.

Challenge
	O(nk) time
***/
//version-1: binary search
public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // check corner cases
		if (pages == null || pages.length == 0 || k <= 0) {
			return 0;
		}
		
		int sum = 0;
		int maxTime = pages[0];
		for ( int current : pages) {
			sum += current;
			maxTime = Math.max(maxTime, current);
		}
		
		int start = maxTime; // at most person can handle to spend the time 
		int end = sum; // at least 1 person can handle to spend the time 
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			
			if (getCopiers(pages, mid) > k) {
				start = mid;
			}
			else {
				end = mid;
			}
		}
		
		if (getCopiers(pages, start) >= k) {
			return start;
		}
		else {
			return end;
		}
    }
	
	private int getCopiers(int[] nums, int target) {
		int count = 1;
		int sum = 0;
		
		for (int num : nums) {
			sum += num;
			
			if (sum > target) {
				sum = num;
				count++;
			}
		}
		
		return count;
	}
}

//version-2: DP
/*
* 设定dp[k][i]为k个抄写员需要多少时间抄完前i本书
  dp[k][i] = min(j = [0, i]){max{dp[k - 1][j], pages[j] + ... + pages[i - 1]}}
		即：
		  -第k个抄写员： 所花时间pages[j] + ... + pages[i - 1]
		  -前k-1个抄写员(s), 所化的总时间dp[k - 1][j] 
		  -在所有分段最大值中的所有可能性中【相反的木桶原理】，取最小值
* time complexity : O(n^2*K), space complexity(n*k)
*/
public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int K) {
        // check coner case
        if (pages == null || pages.length == 0) {
            return 0;
        }
        
        int n = pages.length;
        
        K = Math.min(K, n);
        
        // state
        int[][] dp = new int[K + 1][n + 1];
        
        // initialize
        for (int k = 0; k <= K; k++) {
            for (int i = 0; i <= n; i++) {
                
                if (i == 0) {
                    dp[k][i] = 0;// i workers finish 0 copies
                    continue;
                }
                
                dp[k][i] = Integer.MAX_VALUE;// not finished
            }
        }
        
        // function
        for (int k = 1; k <= K; k++) {// how many copyworkers we need
            for (int i = 1; i <= n; i++) {// how many books has finshed copying
                int sum = 0;
                for (int j = i; j >=0; j--) {
                    if (dp[k - 1][j] != Integer.MAX_VALUE) {
                        //dp[k][i] = min[j= 0..i]{Max{dp[k - 1][j], A[j] + ... + A[i - 1]}}
                        dp[k][i] = Math.min(dp[k][i], Math.max(dp[k - 1][j], sum));
                    }
                    
					// sum = A[j] + ... + A[i - 1]
					sum += j > 0 ? pages[j - 1] : 0;
                    
                }
            }
        }
        
        return dp[K][n];
    }
}