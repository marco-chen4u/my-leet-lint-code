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
