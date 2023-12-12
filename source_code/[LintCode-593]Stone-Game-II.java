/***
* LintCode 593. Stone Game II
There is a stone game. At the beginning of the game, the player picks n piles of stones and forms a circle, 
i.e. the first pile of stones is adjacent to the last pile of stones.

The goal is to combine the stones into a pile according to the following rules:

At each step in the game, 
the player can merge two adjacent piles of stones into a new pile of stones. 

The score is the number of stones in the new stone pile. 
You need to find the smallest total score.

Example 1
    Input:
        [1,1,4,4]
    Output:18
    Explanation:
        1. Merge the first two piles => [2, 4, 4], score +2
        2. Merge the first two piles => [6, 4]，score +6
        3. Merge the last two piles => [10], score +10

Example 2
    Input:
        [1, 1, 1, 1]
    Output:8
    Explanation:
        1. Merge the first two piles => [2, 1, 1], score +2
        2. Merge the last two piles => [2, 2]，score +2
        3. Merge the last two piles => [4], score +4

Similar question
    LintCode 476. Stone Game (https://www.lintcode.com/problem/476/)
    LintCode 741. Calculate Maximum Value II (https://www.lintcode.com/problem/741)

Link: https://www.lintcode.com/problem/593/
***/
/*
step(1)
    int m = n * 2; // *** note the beginning pile is connecting with the ending pile like a circle, so for each pile, there's 2*n -1 piles to merge, it not n-1.
    dp[i][j] = MAX_VALUE
    dp[i][i] = 0; // no stone to merge, only one pile of stone itself
    
step(2)
    dp[i][j] = min score of merge stone game from pile of stones[i:j]
    where 
        for (int k = i; k < j; k++) {
            dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + continuous-sum[i:j])
        }

        continuous-sum[i:j] = prefixSum[j + 1] - prefixSum[i]
	(note: prefixSum[] = new int[m + 1])

step(3)
     int answer = MAX_VALUE;
     for (int i = 0; i < n; i++) {
         answer = min(answer, dp[i][i + n - 1])
     }
     return dp[i][i + n - 1]  *note: i = [0:n)
*/

//solution-1: DP, zone type dp
public class Solution {
    /**
     * @param nums: An integer array
     * @return: An integer
     */
    public int stoneGame2(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int m = n * 2; //connection first and last elements of array, so the iteration for each one to connect would be 2n - 1, so space would be 2n
        int[][] dp = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }

            dp[i][i] = 0;
        }

        int[] prefixSum = new int[m + 1];
        for (int i = 1; i <=m; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[(i - 1)%n];
        }

        for (int len = 2; len <= m; len++) {
            for (int i = 0; i < m - len + 1; i++) {
                int j = i + len - 1;

                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + prefixSum[j + 1] - prefixSum[i]);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.min(result, dp[i][i + n - 1]);
        }

        return result;
    }
}
