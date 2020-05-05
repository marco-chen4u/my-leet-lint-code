/***
* LintCode 110. Minimum Path Sum
Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Example
	Example 1:
		Input:  [[1,3,1],
				 [1,5,1],
				 [4,2,1]]
		Output: 7	
		Explanation:
		Path is: 1 -> 3 -> 1 -> 1 -> 1


	Example 2:
		Input:  [[1,3,2]]
		Output: 6	
		Explanation:  
		Path is: 1 -> 3 -> 2

Notice
	You can only go right or down in the path.
***/
public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
		// check corner case
		if (grid == null || grid.length == 0 || 
			grid[0] == null || grid[0].length == 0) {
			return 0;
		}
			
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		for (int[] row : dp) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = grid[i][j];
					continue;
				}
				
				if (i == 0) {
					dp[i][j] = dp[i][j - 1] + grid[i][j];
					continue;
				}
				
				if (j == 0) {
					dp[i][j] = dp[i - 1][j] + grid[i][j];
					continue;
				}
				
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}
		
		return dp[n - 1][m - 1];
	}
}

public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        // check corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int n = grid.length; // row size
        int m = grid[0].length;
        
        int[][] dp = new int[2][m];
        
        dp[0][0] = grid[0][0];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i % 2][j] = grid[0][0];
                    continue;
                }
                
                dp[i%2][j] = Integer.MAX_VALUE;
                
                if (i > 0) {
                    dp[i % 2][j] = Math.min(dp[i % 2][j], dp[(i - 1)% 2][j] + grid[i][j]);
                }
                
                if (j > 0) {
                    dp[i % 2][j] = Math.min(dp[i % 2][j], dp[(i) % 2][j - 1] + grid[i][j]);
                }
            }
        }
        
        return dp[(n - 1) % 2][m - 1];
    }
}