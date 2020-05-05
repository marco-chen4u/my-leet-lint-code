/***
* LintCode 843. Digital Flip
Given an array of 01. You can flip 1 to be 0 or flip 0 to be 1.
Find the minimum number of flipping steps so that the array meets the following rules:
The back of 1 can be either1 or 0, but0 must be followed by 0.

Example
	Example 1:
		Input: [1,0,0,1,1,1]
		Output: 2
		Explanation: Turn two 0s into 1s.

	Example 2:
		Input: [1,0,1,0,1,0]
		Output: 2
		Explanation: Turn the second 1 and the third 1 into 0.

Notice
	The length of the given array n <= 100000.
***/
public class Solution {
    /**
     * @param nums: the array
     * @return: the minimum times to flip digit
     */
    public int flipDigit(int[] nums) {
        // check corner case
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		// state
		int n = nums.length;
		int[][] dp = new int[n + 1][2];
		
		// initialize
		for (int[] row : dp) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}		
		dp[0][0] = 0;
		dp[0][1] = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 2; j++) {
				int indicatorValue = (nums[i - 1] != j) ? 1 : 0;
				
				for (int k = 0; k < 2; k++) {
					if (k == 0 && j == 1) {
						continue;//The back of 1 can be either1 or 0, but0 must be followed by 0.
					}
					
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + indicatorValue);
				}
			}
		}
		
		return Math.min(dp[n][0], dp[n][1]);
    }
}