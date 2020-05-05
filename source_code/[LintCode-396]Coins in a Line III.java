/***
* LintCode 396. Coins in a Line III
There are n coins in a line, and value of i-th coin is values[i].
Two players take turns to take a coin from one of the ends of the line until there are no more coins left. 
The player with the larger amount of money wins.

Could you please decide the first player will win or lose?

Example
	Example 1:
		Input: [3, 2, 2]
		Output: true
		Explanation: The first player takes 3 at first. Then they both take 2.
	Example 2:
		Input: [1, 20, 4]
		Output: false
		Explanation: The second player will take 20 whether the first player take 1 or 4.

Challenge
	O(1) memory and O(n) time when n is even.
***/
//version-1: Memorized search
public class Solution {
    // fields
    private int[][] sum;
    private int n;
    
    boolean[][] visited;
    int[][] dp;    
    
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // check corner case
        if (values == null || values.length == 0) {
            return false;
        }
        
        n = values.length;
        sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum[i][j] = (i == j) ? values[i] : sum[i][j - 1] + values[j];
            }
        }
        
        visited = new boolean[n][n];
        dp = new int[n][n];
        
        return getTotal(values) /2 < search(0, n - 1);
    }
    
    // helper method
    private int search(int left, int right) {
        // check corner cae
        if (left > right) {
            return 0;
        }
        
        if (left == right) {
            return sum[left][left];
        }
        
        if (visited[left][right]) {
            return dp[left][right];
        }
        
		
		// try to make the rival(the step) to get the least value(not successful)
        dp[left][right] = sum[left][right] - Math.min(search(left + 1, right), 
                                                        search(left, right - 1));
                                                        
        visited[left][right] = true;
        return dp[left][right];
    }
    
    private int getTotal(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return total;
    }
}

//version-2: dp
public class Solution {
	/**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
		// check corner case
		if (values == null || values.length == 0) {
			return true;
		}
		
		int i, j, len;
		
		// state
		int n = values.length;
		int[][] dp = new int[n][n];
		
		// initialize
		for (i = 0; i < n; i++) {
			dp[i][i] = values[i];
		}
		
		// function
		for (len = 2; len <= n; len++) {
			for (i = 0; i <= n - len; i++) {
				j = i + len - 1;
				
				dp[i][j] = Math.max(values[i] - dp[i + 1][j], values[j] - dp[i][j - 1]);
			}
		}
		
		return dp[0][n - 1] >= 0;
	}
}