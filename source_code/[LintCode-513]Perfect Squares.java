/***
* LintCode 513. Perfect Squares(DP)
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 
	Example 1
		Input: 12
		Output: 3
		Explanation: 4 + 4 + 4

	Example 2
		Input: 13
		Output: 2
		Explanation: 4 + 9
***/
/*
* 从最后一步分析： 从当前值i出发，找出最（后）近一个（符合条件）平方数k，
				然后比较当前dp[i]与dp[i - k^2]的值，并取其最小值更新道dp[i]
* f[i] = min[1 <= k^2 <= i]{f(i - k^2) + 1}
* f[0] = 0  因为最小的完全平方数是1，不是0， 所以等于0即不存在
*/
public class Solution {
    /**
     * @param n: a positive integer
     * @return: An integer
     */
    public int numSquares(int n) {
        // state
		int[] dp = new int[n + 1];
				
		// initialize
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for (int i = 1; i <= n; i++) {
			// last perfect sqare value = j * j;
			for (int j = 1; j * j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i - (j * j)] + 1);
			}
		}
    }
}