/***
* LintCode 564. Backpack VI[Combination Sum IV]
Given an integer array nums with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example
	Example1
		Input: nums = [1, 2, 4], and target = 4
		Output: 6
		Explanation:
			The possible combination ways are:
			[1, 1, 1, 1]
			[1, 1, 2]
			[1, 2, 1]
			[2, 1, 1]
			[2, 2]
			[4]

	Example2
		Input: nums = [1, 2], and target = 4
		Output: 5
		Explanation:
			The possible combination ways are:
			[1, 1, 1, 1]
			[1, 1, 2]
			[1, 2, 1]
			[2, 1, 1]
			[2, 2]
Notice
	A number in the array can be used multiple times in the combination.
	Different orders are counted as different combinations.
***/
/*
* 题意： 给定n个正整数： nums[0], nums[1], ..., nums[n - 1],和一个正整数target
		求有多少种组合加起来是target，每隔nums[i]可以用多次。
		
  最后一步： 背包里最后一个物品的重量是多少
  dp[i] = 有多少组合能拼出重量i
  dp[i] = dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... + dp[i - nums[n - 1]]
			即
				如果背包里最后一个物品是nums[0],那么就看有多少种组合能拼出重量i- nums[0]
				如果背包里最后一个物品是nums[1],那么就看有多少种组合能拼出重量i- nums[1]
				如果背包里最后一个物品是nums[2],那么就看有多少种组合能拼出重量i- nums[2]
				...
				如果背包里最后一个物品是nums[n-1],那么就看有多少种组合能拼出重量i- nums[n - 1]
  这道题跟LintCode 669. Coin Change是同一类型的题目

  
*/
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackVI(int[] nums, int target) {
        // check corner case
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		// state
		int[] dp = new int[target + 1];
		
		// initialize
		Arrays.fill(dp, 0);
		dp[0] = 1;// 重量为0的方案数（组合数）
		
		// function
		for (int i = 1; i <= target; i++) {
			
			// update dp[i]
			for (int num : nums) {
				dp[i] += (i >= num) ? dp[i - num] : 0;
			}
		}
		
		// return 
		return dp[target];
    }
}