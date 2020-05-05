/***
* LintCode 114. Unique Paths
A robot is located at the top-left corner of a m x n grid.
The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid.
How many possible unique paths are there?

Example
	Example 1:
		Input: n = 1, m = 3
		Output: 1	
		Explanation: Only one path to target position.
	Example 2:
		Input:  n = 3, m = 3
		Output: 6	
		Explanation:
			D : Down
			R : Right
			1) DDRR
			2) DRDR
			3) DRRD
			4) RRDD
			5) RDRD
			6) RDDR
Notice
	m and n will be at most 100.
***/
public class Solution {
    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // check corner case
        if (m <= 0 && n <= 0) {
            return 0;
        }
        
        // state
        int[][] dp = new int[n][m];
        // initialize
        dp[0][0] = 1;
        for(int i = 1; i < n; i++) {
            dp[i][0] = 1;
        }
        
        for (int j = 1; j < m; j++) {
            dp[0][j] = 1;
        }
        
        // function
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        
        // return
        return dp[n - 1][m - 1];
    }
}