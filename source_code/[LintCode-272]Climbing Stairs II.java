/***
* LintCode 272. Climbing Stairs II
A child is running up a staircase with n steps, 
and can hop either 1 step, 2 steps, or 3 steps at a time. 
Implement a method to count how many possible ways the child can run up the stairs.

Example
	Example 1:
		Input: 3
		Output: 4
		Explanation: 1 + 1 + 1 = 2 + 1 = 1 + 2 = 3 = 3 , there are 4 ways.

	Example 2:
		Input: 4
		Output: 7
		Explanation: 1 + 1 + 1 + 1 = 1 + 1 + 2 = 1 + 2 + 1 = 2 + 1 + 1 = 2 + 2 = 1 + 3 = 3 + 1 = 4 , there are 7 ways.

Clarification
	For n=0, we think the answer is 1.
***/
//version-1: classical dynamic programing, time complexity:O(n), space complexity: O(n)
public class Solution {
	// filed
	private final int DEFAULT_VALUE = 1;
	
    /**
     * @param n: An integer
     * @return: An Integer
     */
    public int climbStairs2(int n) {
		// check corner case
		if (n <= 1) {
			return DEFAULT_VALUE;
		}
		
		if (n == 2) {
			return 2;
		}
		
		// state
		int[] dp = new int[n + 1];
		
		// intialize
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		//dp[3] = dp[2] + dp[1] + dp[0];
		       //1step-up 2step-up 3step-up
			   
		// function
		for (int i = 3; i <= n; i ++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		
		// return
		return dp[n];
	}
}

//version-2: classical dynamic programing + rotated fixed-array, time complexity:O(n), space complexity: O(1), faster!
public class Solution {
	// filed
	private final int DEFAULT_VALUE = 1;
	
    /**
     * @param n: An integer
     * @return: An Integer
     */
    public int climbStairs2(int n) {
		// check corner case
		if (n <= 1) {
			return DEFAULT_VALUE;
		}
		
		if (n == 2) {
			return 2;
		}
		
		// state
		int[] dp = new int[3];
		
		// intialize
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		//dp[3] = dp[2] + dp[1] + dp[0];
		       //1step-up 2step-up 3step-up
			   
		// function
		for (int i = 3; i <= n; i ++) {
			dp[i%3] = dp[(i - 1)%3] + dp[(i - 2)%3] + dp[(i - 3)%3];
		}
		
		// return
		return dp[n%3];
	}
}