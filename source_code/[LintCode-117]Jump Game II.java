/***
* LintCode 117. Jump Game II
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Your goal is to reach the last index in the minimum number of jumps.
Example
	Given array A = [2,3,1,1,4]
	The minimum number of jumps to reach the last index is 2. 
	(Jump 1 step from index 0 to 1, then 3 steps to the last index.)
***/

public class Solution {
    /**
     * @param A: A list of integers
     * @return: An integer
     */
    public int jump(int[] A) {
		// check corner case
		if (A == null || A.length == 0) {
			return 0;
		}
		
		// state
		int n = A.length;
		int[] dp = new int[n];
		// initialize
		dp[0] = 0;
		
		for (int i = 1; i < n; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		
		// function
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (j + A[j] >= i) {
					dp[i] = Math.min(dp[i], dp[j] + 1);
				}
			}
		}
		
		// return
		return (dp[n - 1] == Integer.MAX_VALUE) ? 0 : dp[n - 1];
	}	
}