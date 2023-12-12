/***
* LintCode 476. Stone Game
There is a stone game.At the beginning of the game the player picks n piles of stones in a line.
The goal is to merge the stones in one pile observing the following rules:
    -At each step of the game,the player can merge two adjacent piles to a new pile.
    -The score is the number of stones in the new pile.
    -You are to determine the minimum of the total score.

Example 1
    Input: [3, 4, 3]
    Output: 17

 Example 2
    Input: [4, 1, 1, 4]
    Output: 18
    Explanation:
        1. Merge second and third piles => [4, 2, 4], score = 2
        2. Merge the first two piles => [6, 4]，score = 8
        3. Merge the last two piles => [10], score = 18

Link
    LintCode: https://www.lintcode.com/problem/476

Similar question
    LintCode 593. Stone Game II (https://www.lintcode.com/problem/593)
***/

// solution-1: DFS
public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        // state
        int[][] dp = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        int[][] sum = new int[n][n];

        // initialize
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum[i][j] = (i == j) ? A[i] : sum[i][j - 1] + A[j];
            }
        }

        return search(0, n - 1, visited, dp, sum);
    }
	
    // helper method
    private int search(int left, int right, 
                        boolean[][] visited, int[][] dp, 
                        int[][] sum) {
        if (visited[left][right]) {
            return dp[left][right];
        }		

        if (left == right) {
            visited[left][right] = true;
            return dp[left][right];
        }

        dp[left][right] = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            int value = search(left, i, visited, dp, sum) + 
                        search(i + 1, right, visited, dp, sum) +
                        sum[left][right];

            dp[left][right] = Math.min(dp[left][right], value);
        }

        visited[left][right] = true;
        return dp[left][right];
    }
}

//solution-2: DP, zone type DP
/*
step(1)
    dp[i][j] = MAX_VALUE
    dp[i][i] = 0; // no stone to merge, only one pile of stone itself
    
step(2)
    dp[i][j] = min score of merge stone game from pile of stones[i:j]
    where 
        for (int k = i; k < j; k++) {
            dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + continuous-sum[i:j])
        }

        continuous-sum[i:j] = prefixSum[j + 1] - prefixSum[i]
	(note: prefixSum[] = new int[n + 1])

step(3)
     return dp[0][n - 1]
*/
public class Solution {
    /**
     * @param nums: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int currentVal = nums[i - 1];
            prefixSum[i] = prefixSum[i - 1] + currentVal;
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }

            dp[i][i] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;

                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + prefixSum[j + 1] - prefixSum[i]);
                }
            }
        }

        return dp[0][n - 1];

    }
}	
