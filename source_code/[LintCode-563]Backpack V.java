/***
* LintCode 563. Backpack V
Given n items with size nums[i] which an integer array and all positive numbers.
An integer target denotes the size of a backpack. 

Find the number of possible fill the backpack.

Each item may only be used once

Example 
	Given candidate items [1,2,3,3,7] and target 7,

	A solution set is: 
		[7]
		[1, 3, 3]
	return 2
***/
/*
* 题意：给定n个正整数：num0, num1, num2,..., num(n-1)
	  一个正整数target
	  求有多少种组合加起来时target
	  每一个num（i）只能用一次。
  此题的重点：有多少组合能拼出target，而不是能不能拼出target。
  当然，如果能知道这n种物品有多少种方式拼出0， 有多少种方式拼出1， ... , 有多少种方式拼出target，也就得到了答案。
  确定状态：
		-需要知道n个物品有多少种方式拼出重量w（w=[0, 1, ... , target]）
		-最后一步： 第n个物品（重量num[i - 1]）能否放进入书包
		        情况1： 用前n-1个物品拼出w
				情况2： 用前n-1个物品拼出w-num[i - 1], 再加上最后的物品num[i - 1], 拼出w
				情况1的个数 + 情况2的个数 = 用当前n个物品拼出w的方式
		-状态： dp[i][w] = 用前i个物品有多少种方式拼出重量w
		      dp[i][w] = dp[i - 1][w] + dp[i - 1][w - num(i - 1)]
						-dp[i - 1][w]用前i-1个物品有多少种方式拼出重量w
						-dp[i - 1][w - num(i - 1)]用前i-1个物品有多少种方式拼出重量w - num(i - 1),再加上第i个物品
		 初始条件：dp[0][0] = 1 即0个物品可以有一种方式拼出重量为0
		        dp[0][1...m] = 0 即0个物品不可能拼出大于0的重量
		 计算前1个物品有多少方式拼出重量： dp[1][0], dp[1][1], dp[1][2], ... , dp[1][target]
		 ...
		 计算前n个物品有多少方式拼出重量： dp[n][0], dp[n][1], dp[n][2], ... , dp[n][target]
		 答案： dp[n][target]
*/
//version-1： DP， time complexity O(n * target), space complexity O(n * target)
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] nums, int target) {
        // check corner case
		
		int n = nums.length;
		// state
		int[][] dp = new int[n + 1][target + 1];
		
		// initialize
		dp[0][0] = 1;
		
		// function
		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= target; w++) {
				//用前n-1个物品拼出w
				dp[i][w] = dp[i - 1][w];
				
				//用前n-1个物品拼出w-num[i - 1], 再加上最后的物品num[i - 1], 拼出w
				if (w >= nums[i - 1]) {
					dp[i][w] = dp[i - 1][w] + dp[i - 1][w - nums[i - 1]];
				}
			}
		}
		
		// answer
		return dp[n][target];
    }
}

//version-2： DP with roating Array for optimizing the space utilization， time complexity O(n * target), space complexity O(target)
public class Solution {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] nums, int target) {
        // check corner case
		
		int n = nums.length;
		// state
		int[][] dp = new int[2][target + 1];
		
		// initialize
		dp[0][0] = 1;
		
		// function
		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= target; w++) {
				//用前n-1个物品拼出w
				dp[i % 2][w] = dp[(i - 1) % 2][w];
				
				//用前n-1个物品拼出w-num[i - 1], 再加上最后的物品num[i - 1], 拼出w
				if (w >= nums[i - 1]) {
					dp[i % 2][w] = dp[(i - 1) % 2][w] + dp[(i - 1) % 2][w - nums[i - 1]];
				}
			}
		}
		
		// answer
		return dp[n % 2][target];
    }
}