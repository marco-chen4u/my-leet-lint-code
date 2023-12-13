/***
* LeetCode 877. Stone Game
Alice and Bob play a game with piles of stones. 
There are an even number of piles arranged in a row, 
and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. 
The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. 
Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row.
This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

Example 1
    Input: piles = [5,3,4,5]
    Output: true
    Explanation: 
        Alice starts first, and can only take the first 5 or the last 5.
        Say she takes the first 5, so that t)he row becomes [3, 4, 5].
        If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
        If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
        This demonstrated that taking the first 5 was a winning move for Alice, so we return true.

Example 2
    Input: piles = [3,7,2,3]
    Output: true

Constraints:
    2 <= piles.length <= 500
    piles.length is even.
    1 <= piles[i] <= 500
    sum(piles[i]) is odd.

Link: https://leetcode.com/problems/stone-game/

Similar question
    LeetCode 1140. Stone Game II (https://leetcode.com/problems/stone-game-ii/)
    LeetCode 1563. Stone Game V (https://leetcode.com/problems/stone-game-v/)
    LeetCode 1686. Stone Game VI (https://leetcode.com/problems/stone-game-vi/)
    LeetCode 1690. Stone Game VII (https://leetcode.com/problems/stone-game-vii/)
    LeetCode 1872. Stone Game VIII (https://leetcode.com/problems/stone-game-viii/)
    LeetCode 2029. Stone Game IX (https://leetcode.com/problems/stone-game-ix/)
    LeetCode 2396. Strictly Palindromic Number (https://leetcode.com/problems/strictly-palindromic-number/)
    LeetCode 2786. Visit Array Positions to Maximum Score (https://leetcode.com/problems/visit-array-positions-to-maximize-score/)
***/
//solution-1: dfs + prefix sum
/*
    solve(1, n) 
        [a] pick first item => piles[1] + sum[2:n] - solve(2, n)           => prefix-sum[1:n] - solve(2, n)
        [b] pick last item  => piles[n] + sum[1 : n - 1] - solve(1, n - 1) => prefix-sum[1:n] - solve(1, n - 1)
          so
              solve(1,n) = max(a, b)
              =>
                  solve(1,n) = prefix-sum[1:n] - min(solve(2, n), solve(1, n - 1))

    
*/
class Solution {
    private static int[][] dp;
    private static int[] prefixSum;

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + piles[i - 1];
        }

        int gain = solve(0, n - 1);
        return gain > prefixSum[n] - gain;
    }

    private int solve(int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        dp[i][j] = (prefixSum[j + 1] - prefixSum[i]) - Math.min(solve(i + 1, j), solve(i, j - 1));

        return dp[i][j];
    }
}

//solution-2: DP, gaming type dp. DP + prefix sum
/*
step(1)
    int[][] dp = new int[n][n]
    int[] prefixSum = new int[n + 1]

step(2)
    for (int i = 0; i < n; i++) {
        dp[i][i] = piles[i]
    }

step(3)
    dp[1][n] = sum[1:n] - min(dp[2][n], dp[1][n - 1])

step(4)
    return dp[1][n]
*/
class Solution {

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + piles[i - 1];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    continue;
                }

                int sum = prefixSum[j + 1] - prefixSum[i];
                dp[i][j] = sum - Math.min(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        int gain = dp[0][n - 1];

        return gain > prefixSum[n] - gain;
    }

}
