/***
* LintCode 111. Climbing Stairs
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example
	Example 1:
		Input:  n = 3
		Output: 3	
		Explanation:
			1) 1, 1, 1
			2) 1, 2
			3) 2, 1
			total 3.

	Example 2:
		Input:  n = 1
		Output: 1	
		Explanation:  
			only 1 way.
***/

public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        // check corner case
        if (n <= 1) {
            return n;
        }
        
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        
        return dp[n];
    }
}