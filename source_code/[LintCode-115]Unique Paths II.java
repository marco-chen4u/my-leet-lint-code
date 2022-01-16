/***
* LintCode 115. Unique Paths II
Follow up for "Unique Paths":
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Example 1:
    Input: [[0]]
    Output: 1
Example 2:
    Input:  [[0,0,0],[0,1,0],[0,0,0]]
    Output: 2	
    Explanation:
        Only 2 different path.
Notice
    m and n will be at most 100.
***/
// version-1: DP
public class Solution {
    // constants
    private final int OBSTACLE = 1;
    private final int EMPTY = 0;

    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // check corner cases
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        if (obstacleGrid[0] == null || obstacleGrid[0].length == 0) {
            return 0;
        }

        if (obstacleGrid[0][0] == OBSTACLE) {
            return 0;
        }

        // regular case
        // state
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];

        // initialize
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] !=OBSTACLE) {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int j = 1; j < m; j++) {
            if (obstacleGrid[0][j] != OBSTACLE) {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // function
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] != OBSTACLE) {
                    dp[i][j] += dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }

        // answer
        return dp[n - 1][m - 1];
    }
}
